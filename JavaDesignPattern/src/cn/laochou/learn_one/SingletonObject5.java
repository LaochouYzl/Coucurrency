package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月16日 下午10:30:07
 * @version 1.0
 */
public class SingletonObject5 {
	
	private SingletonObject5() {}
	
	// if you use volatile, you can prevent null point exception
	private static volatile SingletonObject5 instance = null;
	
	public static SingletonObject5 getInstance() {
		if(instance == null) {
			synchronized (SingletonObject5.class) {
				if(instance == null) {
					instance = new SingletonObject5();
				}
			}
		}
		return SingletonObject5.instance;
	}

}
