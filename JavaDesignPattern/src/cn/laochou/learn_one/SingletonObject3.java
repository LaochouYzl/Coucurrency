package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月16日 下午9:50:16
 * @version 1.0
 */
public class SingletonObject3 {
	
	private SingletonObject3() {}

	private static SingletonObject3 instance = null;
	
	// if you use synchronized decorate the method, you will find efficiency will become lower
	// we only synchronized the key component
	public synchronized static SingletonObject3 getInstance() {
		if(instance == null) {
			instance = new SingletonObject3();
		}
		return SingletonObject3.instance;
	}
	
}
