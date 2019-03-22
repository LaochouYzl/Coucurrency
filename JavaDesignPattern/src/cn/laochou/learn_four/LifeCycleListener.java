package cn.laochou.learn_four;

import cn.laochou.learn_four.ObservableRunnable.RunnableEvent;

/** 
 * @author:Laochou
 * @date 2019年3月21日 下午10:48:38
 * @version 1.0
 */
public interface LifeCycleListener {
	
	void onEvent(RunnableEvent event);

}
