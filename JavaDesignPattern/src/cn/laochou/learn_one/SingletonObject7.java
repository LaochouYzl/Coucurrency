package cn.laochou.learn_one;
/** 
 * @author:Laochou
 * @date 2019年3月16日 下午10:39:55
 * @version 1.0
 */
public class SingletonObject7 {
	
	private SingletonObject7() {
	}
	
	private enum Singleton{
		INSTANCE;
		
		private final SingletonObject7 instance;
		
		private Singleton() {
			instance = new SingletonObject7();
		}
		
		public SingletonObject7 getInstance() {
			return instance;
		}
	}
	
	public static void main(String[] args) {
		SingletonObject7.Singleton.INSTANCE.getInstance();
	}

}
