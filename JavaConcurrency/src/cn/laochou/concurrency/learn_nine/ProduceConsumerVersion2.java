package cn.laochou.concurrency.learn_nine;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午11:25:03
 * @version 1.0
 * the pattern of produce and consumer
 */
public class ProduceConsumerVersion2 {
	
	private int i = 0;

	private final Object LOCK = new Object();
	
	private boolean isProduced = false;
	
	private void produce() {
		synchronized (LOCK) {
			// if the thread is already produce the product we need the product is consumed
			System.out.println("current thread is produce");
			if(isProduced) {
				try {
					System.out.println("produce is waiting");
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
//				System.out.println("produce is produce");
				System.out.println("P->"+(i++));
				LOCK.notify();
				isProduced = true;
			}
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			// consumer conduct the product, and notice the produce thread to continue produce product
			System.out.println("current thread is consumer");
			if(isProduced) {
				System.out.println("consumer is consuming");
				System.out.println("C->"+i);
				LOCK.notify();
				isProduced = false;
			}else {
				try {
//					System.out.println("consumer is waiting");
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumerVersion2 produceConsumerVersion1 = new ProduceConsumerVersion2();
		new Thread() {
			@Override
			public void run() {
				while(true)
					produceConsumerVersion1.produce();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while(true)
					produceConsumerVersion1.consumer();
			};
		}.start();
	}
	
}
