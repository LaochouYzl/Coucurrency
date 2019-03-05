package cn.laochou.concurrency.learn_four;

import java.util.Optional;

/** 
 * @author:Laochou
 * @date 2019年3月5日 下午10:12:44
 * @version 1.0
 * 
 */
public class ThreadSimpleAPI2 {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			for(int i = 0; i< 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-Index"+i).ifPresent(System.out::println);
			}
		});
		t1.setPriority(Thread.MAX_PRIORITY);
		Thread t2 = new Thread(()->{
			for(int i = 0; i< 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-Index"+i).ifPresent(System.out::println);
			}
		});
		t2.setPriority(Thread.NORM_PRIORITY);
		Thread t3 = new Thread(()->{
			for(int i = 0; i< 1000; i++) {
				Optional.of(Thread.currentThread().getName()+"-Index"+i).ifPresent(System.out::println);
			}
		});
		t3.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
	}

}
