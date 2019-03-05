package cn.laochou.concurrency.learn_three;

import java.util.Arrays;

/**
 * 	now we need to see the thread
 * @author Administrator
 *
 */
public class CreateThread {

	public static void main(String[] args) {
		Thread t = new Thread("laochou") {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("the thred of created is runing");
			}
		};
		
		// we know all programs it must have a the entrance of the programs
		// it is main method, maybe many people want to know, how does the main thread
		// run, I need to tell you the main method is running in main thread
		t.start();
		System.out.println(t.getThreadGroup());
		System.out.println(Thread.currentThread());
		// get the current have many active thread
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		System.out.println(threadGroup.activeCount()); 
		// now we can to traverse the list to show
		Thread[] list = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(list);
		Arrays.asList(list).forEach(System.out::println);
		
	}
	
}
