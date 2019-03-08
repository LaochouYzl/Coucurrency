package cn.laochou.concurrency.learn_five;

import java.util.stream.IntStream;

/** 
 * @author:Laochou
 * @date 2019年3月6日 下午6:30:43
 * @version 1.0
 */
public class ThreadJoin2 {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			try {
				System.out.println("t1 is running");
				Thread.sleep(10_000);
				System.out.println("t1 is done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		t1.start();
		t1.join(1000);
		IntStream.range(1, 1000)
		.forEach(i -> System.out.println(Thread.currentThread().getName()+"->"+i));
		
		//Thread.currentThread().join();
	}
	
	
}

/**
 * 
 * @author Laochou
 * at the jdk8+ you can implements method in interface
 */
interface Animal{
	public static void eat() {
		System.out.println("all animal can eat something to get grow");
	}
}
