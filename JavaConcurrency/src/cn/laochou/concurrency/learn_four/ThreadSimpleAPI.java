package cn.laochou.concurrency.learn_four;

import java.util.Optional;

/** 
 * @author:Laochou
 * @date 2019年3月5日 下午9:39:15
 * @version 1.0
 * the class be mainly used to know how to use the api of Thread
 */
public class ThreadSimpleAPI {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			Optional.of("hello").ifPresent(System.out::println);
			try {
				Thread.sleep(50_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t1");
		Optional.of(t1.getName()).ifPresent(System.out::println);
		Optional.of(t1.getId()).ifPresent(System.out::println);
		Optional.of(t1.getPriority()).ifPresent(System.out::println);
		t1.start();
		Thread.sleep(10_000);
	}
	
}
