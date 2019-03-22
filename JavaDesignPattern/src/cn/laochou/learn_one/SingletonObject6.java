package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月16日 下午10:37:23
 * @version 1.0
 */
public class SingletonObject6 {
	
	private SingletonObject6() {}
	
	private static class InnerHolder{
		private static final SingletonObject6 instance = new SingletonObject6();
	}
	
	public static SingletonObject6 getInstance() {
		return SingletonObject6.InnerHolder.instance;
	}

}
