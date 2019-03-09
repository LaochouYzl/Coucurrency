package cn.laochou.concurrency.learn_nine;

import java.util.Scanner;

/** 
 * @author:Laochou
 * @date 2019年3月9日 下午1:28:07
 * @version 1.0
 * Wait and notify test
 */
public class WaitAndNotify {
	
	private static boolean isNotify = false;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Object LOCK = new Object();
		new Thread() {
			@Override
			public void run() {
				int flag = 0;
				int times = 0;
				while(true) {
					/*
					 * if(flag == 1) { try { System.out.println("我曾经来过, 在此休息1秒钟, 锁就被偷了"); times++;
					 * if(times == 5) { System.out.println(times); System.exit(0); }
					 * Thread.sleep(5); } catch (InterruptedException e1) { // TODO Auto-generated
					 * catch block e1.printStackTrace(); } }
					 */
					synchronized (LOCK) {
						System.out.println("T1 PLEASE INPUT INSTRUCTION");
						try {
							System.out.println("我曾经来过, 在此休息1秒钟, 锁不释放");
							times++;
							if(times == 5) {
								System.out.println(times);
								System.exit(0);
							}
							Thread.sleep(1_000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String instruct = scanner.next();
						if(instruct.equals("wait")) {
							flag = 1;
							try {
								System.out.println("T1 FRONT PART");
								LOCK.wait();
								System.out.println("T1 IS WAIT");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else if(instruct.equals("notify")) {
							LOCK.notify();
							System.out.println("T1 IS NOTIFY");
						}
					}
				}
				
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while(true) {
					synchronized (LOCK) {
						System.out.println("T2 PLEASE INPUT INSTRUCTION");
						String instruct = "notify";
						if(instruct.equals("wait")) {
							try {
								System.out.println("T2 FRONT PART");
								LOCK.wait();
								System.out.println("T2 IS WAIT");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else if(instruct.equals("notify")) {
							LOCK.notify();
							System.out.println("T2 IS NOTIFY");
						}
					}
				}
				
			}
		}.start();
	}
	
}
