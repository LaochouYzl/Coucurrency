package cn.laochou.concurrency.learn_two;

public class BankRunnable {
	
	public static void main(String[] args) {
		TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
		Thread t1 = new Thread(ticketWindowRunnable, "һ�Ź�̨");
		Thread t2 = new Thread(ticketWindowRunnable, "���Ź�̨");
		Thread t3 = new Thread(ticketWindowRunnable, "���Ź�̨");
		t1.start();
		t2.start();
		t3.start();
	}

}
