package cn.laochou.concurrency.learn_two;

/**
 * bank
 * @author Administrator
 *
 */
public class Bank {

	public static void main(String[] args) {
		TicketWindow ticketWindow1 = new TicketWindow("һ�Ź�̨");
		ticketWindow1.start();
		TicketWindow ticketWindow2 = new TicketWindow("���Ź�̨");
		ticketWindow2.start();
		TicketWindow ticketWindow3 = new TicketWindow("���Ź�̨");
		ticketWindow3.start();
	}
	
}
