package cn.laochou.learn_four;

import java.util.Arrays;

/** 
 * @author:Laochou
 * @date 2019年3月22日 下午12:07:44
 * @version 1.0
 */
public class ThreadLifeCycleClient {
	
	public static void main(String[] args) {
		new ThreadLifeCycleListener().concurrentQuery(Arrays.asList("1", "2"));
	}

}
