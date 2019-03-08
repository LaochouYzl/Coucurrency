package cn.laochou.concurrency.learn_six;
/** 
 * @author:Laochou
 * @date 2019年3月7日 下午9:39:40
 * @version 1.0
 */
public class ThreadServiceTest {
	
	public static void main(String[] args) {
		ThreadService threadService = new ThreadService();
		long start = System.currentTimeMillis();
		threadService.execute(()->{
			// at here we simulate to load big resources
			while(true) {
				
			}
		});
		threadService.shutdown(10000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
