package cn.laochou.learn_two;

import java.util.Optional;
import java.util.stream.IntStream;

/** 
 * @author:Laochou
 * @date 2019年3月17日 下午9:45:26
 * @version 1.0
 */
public class WaitSet {
	
	public static final Object LOCK = new Object();

	public static void main(String[] args) {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			new Thread(String.valueOf(i)) {
				@Override
				public void run() {
					synchronized (LOCK) {
						try {
							Optional.of(Thread.currentThread().getName()+" will come to wait set").ifPresent(System.out::println);
							LOCK.wait();
							Optional.of(Thread.currentThread().getName()+" will leave to wait set").ifPresent(System.out::println);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		});
		
		IntStream.rangeClosed(1, 10).forEach(i ->{
			synchronized (LOCK) {
				LOCK.notify();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
