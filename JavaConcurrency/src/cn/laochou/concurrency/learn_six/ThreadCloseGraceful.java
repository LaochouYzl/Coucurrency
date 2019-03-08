package cn.laochou.concurrency.learn_six;
/** 
 * @author:Laochou
 * @date 2019年3月7日 下午8:07:19
 * @version 1.0
 * how to close the thread use the method of graceful
 */
public class ThreadCloseGraceful {

	private static class Worker extends Thread{
		private volatile boolean start = true;
		
		@Override
		public void run() {
			while(start) {
				
			}
		}
		
		public void shutdown() {
			this.start = false;
		}
	}
	
	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.start();
		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		worker.shutdown();

	}
	
}
