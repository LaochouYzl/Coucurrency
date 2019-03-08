package cn.laochou.concurrency.learn_seven;

/**
 * 	the window for ticket(售票窗口)
 * @author Administrator
 *	为什么要用Runnable呢, 
 */
public class TicketWindow extends Thread{
	
	private final String name;
	
	private static final int MAXSIZE = 500;
	
	private static int index = 1;
	
	private static final Object MONITOR = new Object();
	
	public TicketWindow(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		// at here we can use the synchronized to ensure the safe of program
		while(true) {
			synchronized (MONITOR) {
				if(index > MAXSIZE) {
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("当前柜台是:"+name+"--->"+"当前号码是:"+(index++));
			}
		}
	}

}
