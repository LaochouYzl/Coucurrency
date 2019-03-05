package cn.laochou.concurrency.learn_two;

/**
 * 	the window for ticket(售票窗口)
 * @author Administrator
 *	为什么要用Runnable呢, 
 */
public class TicketWindow extends Thread{
	
	private final String name;
	
	private static final int MAXSIZE = 50;
	
	private static int index = 1;
	
	public TicketWindow(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while(index <= MAXSIZE) {
			System.out.println("当前柜台是:"+name+"--->"+"当前号码是:"+(index++));
		}
	}

}
