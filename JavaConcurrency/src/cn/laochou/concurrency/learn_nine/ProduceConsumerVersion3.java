package cn.laochou.concurrency.learn_nine;
import java.util.stream.Stream;

/** 
 * @author:Laochou
 * @date 2019年3月8日 下午11:25:03
 * @version 1.0
 * the pattern of produce and consumer
 */
public class ProduceConsumerVersion3 {
	
	private int i = 0;

	private final Object LOCK = new Object();
	
	private boolean isProduced = false;
	
	private void produce() {
		synchronized (LOCK) {
			// if the thread is already produce the product we need the product is consumed
			/*
			 * System.out.println(Thread.currentThread().getName()+"->"+(i++));
			 * System.out.println("---------------------------");
			 * System.out.println("current thread is "+Thread.currentThread().getName());
			 * threadGroup = Thread.currentThread().getThreadGroup(); list = new
			 * Thread[threadGroup.activeCount()]; threadGroup.enumerate(list);
			 * Arrays.asList(list).forEach(n ->
			 * System.out.println(n.getName()+"->"+n.getState()));
			 */
			if(isProduced) {
				try {
					System.out.println(Thread.currentThread().getName()+" is waiting");
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println(Thread.currentThread().getName()+" is producing");
				System.out.println(Thread.currentThread().getName()+"->"+i);
				LOCK.notify();
				isProduced = true;
			}
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			// consumer conduct the product, and notice the produce thread to continue produce product
			/*
			 * System.out.println("=============================");
			 * System.out.println("current thread is " + Thread.currentThread().getName());
			 * threadGroup = Thread.currentThread().getThreadGroup(); list = new
			 * Thread[threadGroup.activeCount()]; threadGroup.enumerate(list);
			 * Arrays.asList(list).forEach(n ->
			 * System.out.println(n.getName()+"->"+n.getState()));
			 */
			if(isProduced) {
				System.out.println(Thread.currentThread().getName()+" is consuming");
				System.out.println(Thread.currentThread().getName()+"->"+(i++));
				LOCK.notify();
				isProduced = false;
			}else {
				try {
					System.out.println(Thread.currentThread().getName()+" is waiting");
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumerVersion3 produceConsumerVersion1 = new ProduceConsumerVersion3();
		Stream.of("P1", "P2").forEach(n -> {
			new Thread(n) {
				@Override
				public void run() {
					while(true)
						produceConsumerVersion1.produce();
				}
			}.start();
		});
		Stream.of("C1", "C2").forEach(n -> {
			new Thread(n) {
				@Override
				public void run() {
					while(true)
						produceConsumerVersion1.consumer();
				};
			}.start();
		});
		
	}
	
}
