package cn.laochou.learn_four;

import java.util.List;

import cn.laochou.learn_four.ObservableRunnable.RunnableEvent;

/** 
 * @author:Laochou
 * @date 2019年3月21日 下午10:55:05
 * @version 1.0
 */
public class ThreadLifeCycleListener implements LifeCycleListener{
	
	private final Object LOCK = new Object();
	
	public void concurrentQuery(List<String> ids) {
		if(ids == null || ids.isEmpty()) {
			return;
		}
		ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
			@Override
			public void run() {
				try {
					notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
					System.out.println("query for the id "+id);
					Thread.sleep(1000L);
					notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
				} catch (Exception e) {
					notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
				}
			}
		}, id).start());
	}

	@Override
	public void onEvent(RunnableEvent event) {
		synchronized (LOCK) {
			System.out.println("The runnable ["+event.getThread().getName()+"] data change and  state is ["+event.getState()+"]");
			// an exception is happen
			if(event.getCause() != null) {
				System.out.println("The runnbale ["+event.getThread().getName()+"] process failed");
				event.getCause().printStackTrace();
			}
		}
	}
	
	

}
