package cn.laochou.learn_four;
/** 
 * @author:Laochou
 * @date 2019年3月21日 下午9:59:17
 * @version 1.0
 */
public abstract class ObservableRunnable implements Runnable{

	protected LifeCycleListener listener;
	
	public ObservableRunnable(final LifeCycleListener listener) {
		this.listener = listener;
	}
	
	protected void notifyChange(final RunnableEvent event) {
		listener.onEvent(event);
	}
	
	public enum RunnableState{
		RUNNING, ERROR, DONE;
	}
	
	public static class RunnableEvent{
		private final RunnableState state;
		private final Thread thread;
		private final Throwable cause;
		
		public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
			this.state = state;
			this.thread = thread;
			this.cause = cause;
		}

		public RunnableState getState() {
			return state;
		}

		public Thread getThread() {
			return thread;
		}

		public Throwable getCause() {
			return cause;
		}
		
	}
	
}
