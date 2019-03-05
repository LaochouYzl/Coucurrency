package cn.laochou.concurrency.learn_four;

/**
 * 
 * @author Laochou
 *
 */
public class DaemonThread2 {
	
	public static void main(String[] args) {
		// at here we use the lambda to create a object
		// we must to know the lambda is a living example
		Thread t = new Thread(()->{
			Thread innerThread = new Thread(()->{
				try {
					// 100_000 is mean 100 seconds must jdk1.7+
					System.out.println("the innerThread is running !");
					Thread.sleep(100_000);
					System.out.println("the innerThread is done !");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			innerThread.setDaemon(true);
			innerThread.start();
			try {
				Thread.sleep(2_000);
				System.out.println("T thread finished done !");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		t.setDaemon(true);
		t.start();
		System.out.println("done");
	}

}
