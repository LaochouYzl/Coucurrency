package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月15日 上午11:59:43
 * @version 1.0
 */
public class SingletonObject {
	
	/**
	 * can't lazy load
	 */
	private static final SingletonObject instance = new SingletonObject();
	
	private SingletonObject() {}
	
	public static SingletonObject getInstance() {
		return instance;
	}

}
