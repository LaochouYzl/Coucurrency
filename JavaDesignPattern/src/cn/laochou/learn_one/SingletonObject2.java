package cn.laochou.learn_one;


/** 
 * @author:Laochou
 * @date 2019年3月15日 下午12:02:57
 * @version 1.0
 */
public class SingletonObject2 {
	
	private static SingletonObject2 instance = null;
	
	private static int count = 0;
	
	private SingletonObject2() {}
	
	// at here exists a problem that is if the program run in environment of multithread, it may has lots of instance, only one.
	public static SingletonObject2 getInstance() {
		// lazy load, it mean when you to use the object, the object is created.
		if(instance == null) {
			count++;
			instance = new SingletonObject2();
		}
		return SingletonObject2.instance;
	}
	
	public static void main(String[] args) throws InterruptedException {
		// at here, the problem doesn't show
		Thread t;
		for(int i = 0; i< 100000; i++) {
			System.out.println("new 了 "+i+"个线程");
			t = new Thread(i+"") {
				public void run() {
					SingletonObject2.getInstance();
				};
			};
			t.start();
			t.join();
		}
		System.out.println(count);
	}
}
