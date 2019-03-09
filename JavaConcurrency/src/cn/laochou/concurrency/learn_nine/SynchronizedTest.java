package cn.laochou.concurrency.learn_nine;
/** 
 * @author:Laochou
 * @date 2019年3月9日 下午4:22:47
 * @version 1.0
 * the synchronized key word 
 */
public class SynchronizedTest {

	public static void main(String[] args) {
		
		Object obj = new Object();
		
		new Thread() {
			@Override
			public void run() {
				while(true) {
					synchronized (obj) {
						System.out.println("T1");
					}
				}
			};
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				while(true) {
					synchronized (obj) {
						System.out.println("T2");
						System.exit(0);
					}
				}
			};
		}.start();
		
	}
	
}
