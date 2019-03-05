package cn.laochou.concurrency.learn_four;

/**
 * daemon thread
 * @author Administrator
 *
 */
public class DaemonTread {

	public static void main(String[] args) {
		// use the class of no name to create a object which is extends Thread
		Thread t = new Thread() {
			@Override // override the method of run , that is the unit of execute
			public void run() {
				System.out.println(Thread.currentThread().getName()+"running");
				try {
					Thread.sleep(10000); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"done");
			}
		};
		// this method is made the thread is daemon(it must die when the parent is died)
		t.setDaemon(true);
		t.start();
		System.out.println(Thread.currentThread().getName());
	}
	
}
