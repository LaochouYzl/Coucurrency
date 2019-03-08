package cn.laochou.concurrency.learn_six;
/** 
 * @author:Laochou
 * @date 2019年3月7日 下午7:25:48
 * @version 1.0
 */
public class ThreadInterrupt {
	
	public static void main(String[] args) throws InterruptedException {
		/*
		 * Thread t = new Thread(){
		 * 
		 * @Override public void run() { while(true) {
		 * System.out.println(">>"+this.isInterrupted()); } } }; t.start();
		 * Thread.sleep(100); System.out.println(t.isInterrupted()); t.interrupt();
		 * System.out.println(t.isInterrupted());
		 */
		Thread t = new Thread(){
			@Override
			public void run() {
				while(true) {
//					System.out.println(">>"+this.isInterrupted());
				}
			}
		};
		t.start();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				t.interrupt();
				System.out.println("t is interrupted");
			}
		};
		t2.start();
		t.join();
	}

}
