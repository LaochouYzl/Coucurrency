package cn.laochou.concurrency.learn_seven;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午10:27:29
 * @version 1.0
 */
public class Static {
	
	// if you synchronized the static block, at class load initialize you will find something 
	static {
		synchronized (Static.class) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void m() {
		System.out.println("m"+Thread.currentThread().getName());
		test();
	}
	
	public static void test() {
		System.out.println("test" + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		Static.m();
	}

}
