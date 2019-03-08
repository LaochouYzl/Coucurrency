package cn.laochou.concurrency.learn_seven;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午10:00:26
 * @version 1.0
 */
public class SynchronizedStaticTest {
	
	public static void main(String[] args) {
		new Thread("T1") {
			@Override
			public void run() {
				try {
					SynchronizedStatic.m1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("T2") {
			@Override
			public void run() {
				try {
					SynchronizedStatic.m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread("T3") {
			@Override
			public void run() {
				try {
					SynchronizedStatic.m3();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
