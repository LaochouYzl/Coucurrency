package cn.laochou.learn_four;
/** 
 * @author:Laochou
 * @date 2019��3��21�� ����9:47:11
 * @version 1.0
 */
public class BinaryObserver extends Observer{

	public BinaryObserver(Subject subject) {
		super(subject);
	}

	@Override
	public void update() {
		System.out.println("Binary String : " + Integer.toBinaryString(subject.getState()) );
	}
	
	

}
