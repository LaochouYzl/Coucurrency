package cn.laochou.concurrency.learn_ten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/** 
 * @author:Laochou
 * @date 2019年3月10日 下午7:04:35
 * @version 1.0
 */
public class BooleanLock implements Lock{
	
	// the initValue is true indicated the lock has be get
	// the initValue is false indicated the lock is fee, other thread can get it
	private boolean initValue;
	
	// record thread that is running( in short is record thread that is get the lock)
	private Thread currentThread;
	
	private Collection<Thread> blockedThreadCollection = new ArrayList<Thread>();
	
	public BooleanLock() {
		this.initValue = false;
	}

	@Override
	public synchronized void lock() throws InterruptedException {
		while(initValue) {
			blockedThreadCollection.add(Thread.currentThread());
			this.wait();
		}
		blockedThreadCollection.remove(Thread.currentThread());
		this.currentThread = Thread.currentThread();
		this.initValue = true;
	}

	@Override
	public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
		if(mills <= 0)
			lock();
		long hasRemaining = mills;
		long endTime = System.currentTimeMillis();
		while(initValue) {
			if(hasRemaining <= 0) {
				throw new TimeOutException("time out");
			}
			blockedThreadCollection.add(Thread.currentThread());
			this.wait(mills);
			hasRemaining = endTime  - System.currentTimeMillis();
		}
		this.initValue = true;
		this.currentThread = Thread.currentThread();
	}

	// at here exists a problem that is other thread is also can release the lock
	// so we only allow current thread that is running to release it;
	@Override
	public synchronized void unlock() {
		if(currentThread == Thread.currentThread()) {
			this.initValue = false;
			Optional.of(Thread.currentThread()+"released the lock monitor").ifPresent(System.out::println);;
			this.notifyAll();
		}
	}

	@Override
	public Collection<Thread> getBlockedThreads() {
		return Collections.unmodifiableCollection(blockedThreadCollection);
	}

	@Override
	public Integer getBlockedSize() {
		return blockedThreadCollection.size();
	}

}
