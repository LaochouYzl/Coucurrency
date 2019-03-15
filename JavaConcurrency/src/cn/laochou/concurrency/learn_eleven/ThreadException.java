package cn.laochou.concurrency.learn_eleven;
/** 
 * @author:Laochou
 * @date 2019年3月11日 下午11:16:17
 * @version 1.0
 */
public class ThreadException {
	
	private final static int A = 10;
	private final static int B = 0;
	
	public static void main(String[] args) {
		Thread t = new Thread(()->{
			try {
				Thread.sleep(1_000);
				int result = A / B;
				System.out.println(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t.setUncaughtExceptionHandler((thread, e)->{
			System.out.println(e);
			System.out.println(thread);
		});
		
		t.start();
	}

}
