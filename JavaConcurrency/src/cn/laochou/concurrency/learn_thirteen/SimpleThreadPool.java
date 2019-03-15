package cn.laochou.concurrency.learn_thirteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/** 
 * @author:Laochou
 * @date 2019年3月12日 下午8:27:15
 * @version 1.0
 */
public class SimpleThreadPool extends Thread{
	
	private int size;
	
	private final int queueSize;
	
	private final DiscardPolicy discardPolicy;
	
	private final static int DEFAULT_TASK_QUEUE_SIZE = 100;
	
	// the queue of task
	private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<Runnable>(); 
	
	// sequence
	private static volatile int seq = 0;
	
	// the prefix name of thread , the thread name is (prefix + sequence)
	private final static String THREAD_PREFIX = "SIMPLE-THREAD-POOL-";
	
	// create a thread group
	private final static ThreadGroup GROUP = new ThreadGroup("Pool-Group"); 
	
	// the list of threads
	private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<WorkerTask>();
	
	// record used threads times
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	private volatile boolean destory = false;
	
	private int min;
	private int active;
	private int max;
	
	// at here we define a default policy to refuse some extra tasks
	public static final DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
		throw new DiscardException("discard some extra tasks");
	};
	
	public SimpleThreadPool() {
		this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
	}
	
	public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
		this.min = min;
		this.active = active;
		this.max = max;
		this.queueSize = queueSize;
		this.discardPolicy = discardPolicy;
		init();
	}
	
	private void init() {
		for(int i = 0; i<this.min; i++) {
			createWorkerTask();
		}
		this.size = min;
		// start
		this.start();
	}
	
	public void submitTask(Runnable runnable) {
		if(destory) {
			throw new IllegalStateException("the thread pool already destory and not allow to sumbit task");
		}
		synchronized (TASK_QUEUE) {
			if(TASK_QUEUE.size() > queueSize) {
				discardPolicy.discard();
			}
			TASK_QUEUE.addLast(runnable);
			TASK_QUEUE.notifyAll();
		}
	}
	
	@Override
	public void run() {
		while(!destory) {
			System.out.printf("POOL #MIN:%d, #ACTIVE:%d, #MAX:%d, #CURRENTSIZE:%d, #QUEUESIZE:%d\n", this.min, this.active, this.max, this.size, TASK_QUEUE.size());
			try {
				Thread.sleep(2_000L);
				// make the thread size from min to active
				if(TASK_QUEUE.size() > this.active && this.size < this.active) {
					for(int i = this.size; i < active; i++) {
						createWorkerTask();
					}
					this.size = active;
					System.out.println("The Pool increments to active");
				}
				// make the thread size from size to max
				else if(TASK_QUEUE.size() > this.max && this.size < this.max) {
					for(int i = this.size; i < max; i++) {
						createWorkerTask();
					}
					this.size = max;
					System.out.println("The Pool increments to max");
				}
				
				synchronized (THREAD_QUEUE) {
					if(TASK_QUEUE.isEmpty() && this.size > this.active) {
						System.out.println("=======Reduce=======");
						int releaseSize = size - active;
						for(Iterator<WorkerTask> iterator = THREAD_QUEUE.iterator(); iterator.hasNext();) {
							if(releaseSize <= 0) {
								break;
							}
							WorkerTask workerTask = iterator.next();
							if(workerTask.getTaskStatus() == TaskStatus.BLOCKED) {
								workerTask.close();
								workerTask.interrupt();
								iterator.remove();
								releaseSize--;
							}
						}
						this.size = active;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createWorkerTask() {
		WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX+(seq++));
		task.start();
		THREAD_QUEUE.add(task);
	}
	
	// close thread pool , because thread pool can't already is open, it will worse many resources.
	public void shutDown() throws InterruptedException {
		while(!TASK_QUEUE.isEmpty()) {
			Thread.sleep(500);
		}
		synchronized (THREAD_QUEUE) {
			int val = THREAD_QUEUE.size();
			while(val > 0) {
				for(WorkerTask workerTask : THREAD_QUEUE) {
					if(workerTask.getTaskStatus() == TaskStatus.BLOCKED) {
						workerTask.interrupt();
						workerTask.close();
						val--;
					}else {
						Thread.sleep(10);
					}
				}
			}
		}
		this.destory = true;
		System.out.println("the thread pool is disposed");
	}
	
	private enum TaskStatus{
		FREE, RUNNING, BLOCKED, DEAD
	}
	
	public static class DiscardException extends RuntimeException{
		
		private static final long serialVersionUID = 1L;

		public DiscardException(String message) {
			super(message);
		}
	}
	
	// its exists is to prevent have many task to do to make our personal computer collapse, so we need some policy
	// when we can't reception some tasks, we need to refuse them, not understand
	public static interface DiscardPolicy{
		void discard();
	}
	
	
	
	public int getSize() {
		return size;
	}

	public int getQueueSize() {
		return queueSize;
	}
	
	
	
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public boolean isDestory() {
		return this.destory;
	}

	// work thread
	private static class WorkerTask extends Thread{
		private volatile TaskStatus taskStatus = TaskStatus.FREE;
		
		public WorkerTask(ThreadGroup group, String name) {
			super(group, name);
		}
		
		public TaskStatus getTaskStatus() {
			return this.taskStatus;
		}
		
		public void close() {
			this.taskStatus = TaskStatus.DEAD;
		}
		
		@Override
		public void run() {
			// when current thread is not dead, you can work
			OUTER:
			while(this.taskStatus != TaskStatus.DEAD) {
				Runnable runnable;
				synchronized (TASK_QUEUE) {
					while(TASK_QUEUE.isEmpty()) {
						try {
							taskStatus = TaskStatus.BLOCKED;
							TASK_QUEUE.wait();
						} catch (InterruptedException e) {
							System.out.println(Thread.currentThread().getName()+" is break");
							break OUTER;
						}
					}
					
					runnable = TASK_QUEUE.removeFirst();
					if(runnable != null) {
//						getThreadGroupStatus(100);
						taskStatus = TaskStatus.RUNNING;
						runnable.run();
						taskStatus = TaskStatus.FREE;
					}
					
				}
			}
			System.out.println(Thread.currentThread().getName()+"end");
		}
	}
	
	/**
	 * get current thread group all thread's status
	 * @param i
	 */
	public static void getThreadGroupStatus(int i){
		System.out.println("-------第"+i+"次-----当前GROUP所有线程的状态----------------");
		Thread[] list = new Thread[GROUP.activeCount()];
		GROUP.enumerate(list);
		Arrays.asList(list).forEach(item -> {
			System.out.println(item.getName()+" : "+item.getState());
		});
	}
	
	/**
	 * record every thread has use how many times
	 * @return
	 */
	private static HashMap<String, Integer> getCurrentThreadNumberMap() {
		String number = Thread.currentThread().getName().substring(SimpleThreadPool.THREAD_PREFIX.length());
		if(map.get(number) == null) {
			map.put(number, 1);
		}else {
			map.put(number, map.get(number)+1);
		}
		return (HashMap<String, Integer>) map;
	}
	
	public static void main(String[] args) throws InterruptedException {
		SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
		int i = 0;
		for(; i<40; i++) {
			simpleThreadPool.submitTask(()->{
				getCurrentThreadNumberMap();
				System.out.println("The runnable be serviced by "+Thread.currentThread().getName()+" start");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				System.out.println("The runnable be serviced by "+Thread.currentThread().getName()+" finished");
			});
		}
		
		for(java.util.Map.Entry<String, Integer> entry:map.entrySet()) {
			System.out.println("key : "+entry.getKey()+" : value : "+entry.getValue());
		}
		Thread.sleep(20000);
		simpleThreadPool.shutDown();
		 
	}

}
