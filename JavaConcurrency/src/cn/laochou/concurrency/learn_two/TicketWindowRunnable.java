package cn.laochou.concurrency.learn_two;

/**
 *  	��Ȼ��Thread�Ĵ���, Ϊʲô��ҪRunnable�Ĵ�����, �������, ��˵��:
 *  	���ʹ����Thread, ��ôҵ���������ظ�, ��ʹ��Runnable�Ͳ��������, ������Ϊҵ����붼�Ǵ���һ��Runnable����
 * @author Administrator
 *
 */
public class TicketWindowRunnable implements Runnable{
	
	private static int index = 1;
	private static final int MAX = 50;

	@Override
	public void run() {
		while(index <= MAX) {
			System.out.println(Thread.currentThread().getName() + "��ȡ�ĺ�����: "+(index++));
		}
	}

}
