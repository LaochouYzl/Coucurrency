package cn.laochou.concurrency.learn_nine;

import java.util.stream.Stream;

/** 
 * @author:Laochou
 * @date 2019年3月10日 下午2:37:17
 * @version 1.0
 * the class is to indicate the difference of @{code wait} and @{code sleep}
 */
public class DifferenceOfSleepAndWait {
	
	/**
	 * new please let me to write the difference of the method of sleep and wait:
	 * 	1.sleep is the method of Thread, but the wait is the method of Object.
	 * 	2.sleep will not release the object monitor(Lock), but the wait will release the monitor and add to the Object monitor waiting queue.
	 * 	3.use sleep not depend on the monitor, but wait need.
	 * 	4.The sleep method not need be wake up, but wait need.
	 */
	
	private final static Object object = new Object();
	
	public static void main(String[] args) {
		Stream.of("T1", "T2").forEach(name -> {
			new Thread(name) {
				@Override
				public void run() {
					m1();
				}
			}.start();
		});
	}
	
	public static void m1() {
		// synchronized block is singleton you must know it, so if you use lots of it in you program, your program will become very slow
		synchronized (object) {
			try {
				System.out.println(Thread.currentThread().getName()+"enter");
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void m2() {
		synchronized (object) {
			try {
				System.out.println(Thread.currentThread().getName()+"enter");
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
