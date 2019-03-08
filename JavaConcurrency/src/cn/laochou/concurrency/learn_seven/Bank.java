package cn.laochou.concurrency.learn_seven;

/**
 * bank
 * @author Administrator
 *
 */
public class Bank {

	public static void main(String[] args) {
		TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
		TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
		TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
		TicketWindow ticketWindow4 = new TicketWindow("四号柜台");
		TicketWindow ticketWindow5 = new TicketWindow("五号柜台");
		TicketWindow ticketWindow6 = new TicketWindow("六号柜台");
		ticketWindow1.start();
		ticketWindow2.start();
		ticketWindow3.start();
		ticketWindow4.start();
		ticketWindow5.start();
		ticketWindow6.start();
	}
	
}
