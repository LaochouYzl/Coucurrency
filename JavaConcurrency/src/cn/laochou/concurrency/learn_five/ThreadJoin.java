package cn.laochou.concurrency.learn_five;

import java.util.Optional;
import java.util.stream.IntStream;

/** 
 * @author:Laochou
 * @date 2019年3月5日 下午10:30:23
 * @version 1.0
 * the class be mainly used to use the method of join and to analyze it
 */
public class ThreadJoin {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			IntStream.range(1, 1000)
			.forEach(i -> System.out.println(Thread.currentThread().getName()+"->"+i));
		});
		Thread t2 = new Thread(()->{
			IntStream.range(1, 1000)
			.forEach(i -> System.out.println(Thread.currentThread().getName()+"->"+i));
		});
		t1.start();
		t2.start();
		// the method of join is for the current thread, it means only in current thread 
		// to wait the other threads to finished their tasks
		t1.join();
		t2.join();
		Optional.of("All of tasks finish done .").ifPresent(System.out::println);
		IntStream.range(1, 1000)
		.forEach(i -> System.out.println(Thread.currentThread().getName()+"->"+i));
	}

}
