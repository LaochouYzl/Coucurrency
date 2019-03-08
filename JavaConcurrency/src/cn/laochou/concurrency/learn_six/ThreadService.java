package cn.laochou.concurrency.learn_six;
/** 
 * @author:Laochou
 * @date 2019年3月7日 下午9:00:07
 * @version 1.0
 * when you learn thread, you must to know a knowledge that is all method to be used is a thread to do.
 */
public class ThreadService {
	
	private Thread executeThread;
	
	// a flag for the action
	private boolean finshed = false;
	
	// the method of execute
	public void execute(Runnable task) {
		// the thread be mainly to create some threads to do something
		executeThread = new Thread() {
			@Override
			public void run() {
				Thread t = new Thread(task);
				// set the daemon must at the before of thread start
				t.setDaemon(true);
				t.start();
				try {
					// the method of join is make the thread of parent to wait current thread finished it's work
					// now let we to get the dot of the method of join
					t.join();
					finshed = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executeThread.start();
	}
	
	// the method of shutdown
	@SuppressWarnings("static-access")
	public void shutdown(long millions) {
		long currentTime = System.currentTimeMillis();
		while(!finshed) {
			// judge the time
			if(System.currentTimeMillis() - currentTime > millions) {
				System.out.println("the task is overtime , we need to manual-lock");
				// interrupt the thread
				executeThread.interrupt();
				break;
			}
			try {
				executeThread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("the execute thread is interrupt");
				break;
			}
		}
		finshed = false;
	}

}
