package cn.laochou.concurrency.learn_ten;
/** 
 * @author:Laochou
 * @date 2019年3月10日 下午6:33:12
 * @version 1.0
 */

import java.util.Collection;

public interface Lock {
	
	public class TimeOutException extends Exception{
		private static final long serialVersionUID = 1L;

		public TimeOutException(String message) {
			super(message);
		}
	}
	
	void lock() throws InterruptedException;
	
	void lock(long mills) throws InterruptedException, TimeOutException;
	
	void unlock();
	
	Collection<Thread> getBlockedThreads();
	
	Integer getBlockedSize();

}
