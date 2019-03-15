package cn.laochou.concurrency.learn_seven;
/** 
 * @author:Laochou
 * @date 2019��3��8�� ����9:58:35
 * @version 1.0
 * if you use the synchronized to decorate the static method, the monitor is the class
 * if you use the synchronized to decorate the method of object , the monitor is the object
 */
public class SynchronizedStatic {
	
	static {
		System.out.println("static" + Thread.currentThread().getName());
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void m1() throws InterruptedException {
		Thread.sleep(10_000);
		System.out.println("m1"+Thread.currentThread().getName());
		
	}
	
	public synchronized static void m2() throws InterruptedException {
		System.out.println("m2"+Thread.currentThread().getName());
		Thread.sleep(10_000);
	}
	
	public static void m3() throws InterruptedException {
		System.out.println("m3"+Thread.currentThread().getName());
		Thread.sleep(10_000);
	}

}
