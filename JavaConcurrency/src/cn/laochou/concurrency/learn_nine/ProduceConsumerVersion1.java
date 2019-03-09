package cn.laochou.concurrency.learn_nine;
/** 
 * @author:Laochou
 * @date 2019年3月8日 下午11:25:03
 * @version 1.0
 * the pattern of produce and consumer
 */
public class ProduceConsumerVersion1 {
	
	private int i = 0;

	private final Object LOCK = new Object();
	
	private void produce() {
		synchronized (LOCK) {
			System.out.println("P->"+(i++));
		}
	}
	
	private void consumer() {
		synchronized (LOCK) {
			System.out.println("C->"+i);
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumerVersion1 produceConsumerVersion1 = new ProduceConsumerVersion1();
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
