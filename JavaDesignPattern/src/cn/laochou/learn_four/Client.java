package cn.laochou.learn_four;
/** 
 * @author:Laochou
 * @date 2019年3月21日 下午9:51:17
 * @version 1.0
 */
public class Client {
	
	public static void main(String[] args) {
		final Subject subject = new Subject();
		new BinaryObserver(subject);
		new OctalObserver(subject);
		System.out.println("============");
		subject.setState(10);
		System.out.println("============");
		subject.setState(10);
		System.out.println("============");
		subject.setState(20);
	}

}
