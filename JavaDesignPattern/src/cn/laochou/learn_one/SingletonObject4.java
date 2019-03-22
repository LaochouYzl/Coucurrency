package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月16日 下午9:59:40
 * @version 1.0
 */
public class SingletonObject4 {
	
	private SingletonObject4() {}
	
	private static SingletonObject4 instance = null;
	
	// double check
	public static SingletonObject4 getInstance() {
		if(instance == null) {
			// at here , only two threads were locked, others isn't
			synchronized (SingletonObject4.class) {
				if(instance == null) {
					instance = new SingletonObject4();
				}
			}
		}
		return SingletonObject4.instance;
	}
	
	

}
