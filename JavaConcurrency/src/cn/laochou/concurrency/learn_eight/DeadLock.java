package cn.laochou.concurrency.learn_eight;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午10:40:59
 * @version 1.0
 * implements dead lock 
 */
public class DeadLock {
	
	private Service service;
	
	private Object lock = new Object();
	
	public DeadLock(Service service) {
		this.service = service;
	}
	
	public void m1() {
		synchronized (lock) {
			System.out.println("m1");
			service.s1();
		}
	}

	public void m2() {
		synchronized (lock) {
			System.out.println("m2");
		}
	}
	
	public static void main(String[] args) {
		Service service = new Service();
		DeadLock deadLock = new DeadLock(service);
		service.setDeadLock(deadLock);
		new Thread() {
			@Override
			public void run() {
				while(true) {
					deadLock.m1();
				}
			};
		}.start();
		new Thread() {
			@Override
			public void run() {
				while(true) {
					service.s2();
				}
			};
		}.start();
	}

}
