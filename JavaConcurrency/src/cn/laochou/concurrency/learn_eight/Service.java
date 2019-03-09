package cn.laochou.concurrency.learn_eight;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午10:56:20
 * @version 1.0
 */
public class Service {
	
	private Object object = new Object();
	
	private DeadLock deadLock;

	public void s1() {
		synchronized (object) {
			System.out.println("s1 =============");
		}
	}
	
	public void s2() {
		synchronized (object) {
			System.out.println("s2 =============");
			deadLock.m2();
		}
	}
	
	public void setDeadLock(DeadLock deadLock) {
		this.deadLock = deadLock;
	}

}
