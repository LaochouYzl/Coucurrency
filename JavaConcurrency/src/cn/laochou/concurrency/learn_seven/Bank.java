package cn.laochou.concurrency.learn_seven;

/**
 * bank
 * @author Administrator
 *
 */
public class Bank {

	public static void main(String[] args) {
		TicketWindow ticketWindow1 = new TicketWindow("һ�Ź�̨");
		TicketWindow ticketWindow2 = new TicketWindow("���Ź�̨");
		TicketWindow ticketWindow3 = new TicketWindow("���Ź�̨");
		TicketWindow ticketWindow4 = new TicketWindow("�ĺŹ�̨");
		TicketWindow ticketWindow5 = new TicketWindow("��Ź�̨");
		TicketWindow ticketWindow6 = new TicketWindow("���Ź�̨");
		ticketWindow1.start();
		ticketWindow2.start();
		ticketWindow3.start();
		ticketWindow4.start();
		ticketWindow5.start();
		ticketWindow6.start();
	}
	
}
