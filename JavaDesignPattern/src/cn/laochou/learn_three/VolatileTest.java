package cn.laochou.learn_three;
/** 
 * @author:Laochou
 * @date 2019年3月17日 下午10:50:23
 * @version 1.0
 * now please let us to understand volatile
 */
public class VolatileTest {
	
	private volatile static int INIT_VALUE = 0;
	
	public final static int MAX_LIMIT = 5;
	
	public static void main(String[] args) {
		new Thread(()->{
			int localValue = INIT_VALUE;
			while(localValue < MAX_LIMIT) {
				if(localValue != INIT_VALUE) {
					System.out.printf("The value update to [%d]", INIT_VALUE);
				}
			}
			
		}, "READER").start();
		
		new Thread(()->{
			int localValue = INIT_VALUE;
			while(INIT_VALUE < MAX_LIMIT) {
				System.out.printf("update the value update to [%d]", ++localValue);
				INIT_VALUE = localValue;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "UPDATER").start();
	}

}
