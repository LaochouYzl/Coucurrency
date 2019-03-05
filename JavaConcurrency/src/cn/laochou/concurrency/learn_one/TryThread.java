package cn.laochou.concurrency.learn_one;

/**
 * 尝试使用Thread创建一个线程
 * @author Administrator
 *
 */
public class TryThread {
	
	
	public static void main(String[] args) {
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000 * 10L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("I'm thread, I' doing somethings");
			}
		};
		t.start();
	}

}
