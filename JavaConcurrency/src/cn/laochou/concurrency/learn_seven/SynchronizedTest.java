package cn.laochou.concurrency.learn_seven;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午12:54:04
 * @version 1.0
 * test the synchronized
 */
public class SynchronizedTest {
	
	private final static Object MOINTER = new Object();
	
	
	public static void main(String[] args) {
		Runnable runnable = ()->{
			synchronized (MOINTER) {
				try {
					Thread.sleep(10_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t1.start();
		t2.start();
	}

}
