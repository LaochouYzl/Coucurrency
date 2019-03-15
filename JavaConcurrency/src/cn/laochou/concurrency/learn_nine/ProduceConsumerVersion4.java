package cn.laochou.concurrency.learn_nine;

import java.util.stream.Stream;

/** 
 * @author:Laochou
 * @date 2019年3月8日 下午11:25:03
 * @version 1.0
 * the pattern of produce and consumer
 */
public class ProduceConsumerVersion4 {
	
	private int i = 0;

	private final Object LOCK = new Object();
	
	private boolean isProduced = false;
	
	private void produce() {
		synchronized (LOCK) {
			// if the thread is already produce the product we need the product is consumed
			System.out.println("current thread is "+Thread.currentThread().getName());
			while(isProduced) {
				try {
					System.out.println("produce is waiting");
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"->"+(i++));
			LOCK.notifyAll();
			isProduced = true;
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			// consumer conduct the product, and notice the produce thread to continue produce product
			System.out.println("current thread is " + Thread.currentThread().getName());
			while(!isProduced) {
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("consumer is consuming");
			System.out.println(Thread.currentThread().getName()+"->"+i);
			LOCK.notifyAll();
			isProduced = false;
			
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumerVersion4 produceConsumerVersion1 = new ProduceConsumerVersion4();
		Stream.of("P1", "P2").forEach(n -> {
			new Thread(n) {
				@Override
				public void run() {
					while(true)
						produceConsumerVersion1.produce();
				}
			}.start();
		});
		Stream.of("C1", "C2").forEach(n -> {
			new Thread(n) {
				@Override
				public void run() {
					while(true)
						produceConsumerVersion1.consumer();
				};
			}.start();
		});
		
	}
	
}
