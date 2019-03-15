package cn.laochou.concurrency.learn_eleven;
/** 
 * @author:Laochou
 * @date 2019年3月11日 下午8:39:48
 * @version 1.0
 */
public class ExitCaptrue {
	
	public static void main(String[] args){
		// can release resource when our program meet some exceptions
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			System.out.println("The application will be exit");
			notifyAndRealease();
		}));
		int i = 0;
		while(true) {
			try {
				Thread.sleep(1_000);
				System.out.println("I'm working");
			} catch (InterruptedException e) {
				// ignore
			}
			i++;
			if( i > 20) {
				throw new RuntimeException("error");
			}
		}
	}

	private static void notifyAndRealease() {
		System.out.println("notify to admin");
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			
		}
		System.out.println("will release resources(socket, file, connection)");
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			
		}
		System.out.println("Release end");
	}

}
