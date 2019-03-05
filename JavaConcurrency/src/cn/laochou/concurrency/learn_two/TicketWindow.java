package cn.laochou.concurrency.learn_two;

/**
 * 	the window for ticket(��Ʊ����)
 * @author Administrator
 *	ΪʲôҪ��Runnable��, 
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
			System.out.println("��ǰ��̨��:"+name+"--->"+"��ǰ������:"+(index++));
		}
	}

}
