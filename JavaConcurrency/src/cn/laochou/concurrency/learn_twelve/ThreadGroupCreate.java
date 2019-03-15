package cn.laochou.concurrency.learn_twelve;

import java.util.Arrays;

/** 
 * @author:Laochou
 * @date 2019年3月12日 下午3:37:12
 * @version 1.0
 */
public class ThreadGroupCreate {
  
	// if you want to learn thread, you must know jvm will help you create a main thread to execute program as a extrance
	public static void main(String[] args) {
		// create a threadGroup, when you create a threadGroup if you don't assign the otherGroup as its parent, it will
		// put the thread that is called it as its parent.
		ThreadGroup threadGroup = new ThreadGroup("TG1");
		Thread t = new Thread(threadGroup, "T1") {
			@Override
			public void run() {
				try {
					Thread.sleep(10_000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * System.out.println("-----"+Thread.currentThread().getName());
				 * System.out.println("-----"+Thread.currentThread().getThreadGroup());
				 */
			}
		};
		t.start();
		ThreadGroup threadGroup2 = new ThreadGroup("TG2");
		/*
		 * System.out.println(">>>>>>"+Thread.currentThread().getName());
		 * System.out.println(">>>>>>"+Thread.currentThread().getThreadGroup().getName()
		 * ); System.out.println(">>>>>>"+threadGroup.getParent().getName());
		 * System.out.println(">>>>>>"+threadGroup2.getParent().getName());
		 */
		 
		// at jdk-api you can find some message that say
		
		Thread t2 = new Thread(threadGroup2, "T2") {
			public void run() {
				// now we will call on threadgroup1's attribute
				System.out.println("+++++++"+threadGroup.activeCount());
				Thread[] t = new Thread[threadGroup.activeCount()];
				threadGroup.enumerate(t);
				Arrays.asList(t).forEach(System.out::println);
			};
		};
		t2.start();
	}
	
}
