package cn.laochou.concurrency.learn_seven;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午7:29:09
 * @version 1.0
 */
public class SynchronizedThis {
	public static void main(String[] args) {
		ThisLock thisLock = new ThisLock();
		new Thread("T1") {
			@Override
			public void run() {
				thisLock.m1();
			}
		}.start();
		new Thread("T2") {
			@Override
			public void run() {
				thisLock.m2();
			}
		}.start();
	}
	
}

class ThisLock{
	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void m2() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
