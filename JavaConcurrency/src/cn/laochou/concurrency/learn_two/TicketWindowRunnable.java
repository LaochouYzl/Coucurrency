package cn.laochou.concurrency.learn_two;

/**
 *  	既然有Thread的存在, 为什么还要Runnable的存在呢, 这个问题, 我说下:
 *  	如果使用了Thread, 那么业务代码存在重复, 而使用Runnable就不会存在了, 那是因为业务代码都是存在一个Runnable里面
 * @author Administrator
 *
 */
public class TicketWindowRunnable implements Runnable{
	
	private static int index = 1;
	private static final int MAX = 50;

	@Override
	public void run() {
		while(index <= MAX) {
			System.out.println(Thread.currentThread().getName() + "获取的号码是: "+(index++));
		}
	}

}
