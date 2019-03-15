package cn.laochou.concurrency.learn_ten;

import java.util.Optional;
import java.util.stream.Stream;

import cn.laochou.concurrency.learn_ten.Lock.TimeOutException;

/** 
 * @author:Laochou
 * @date 2019年3月10日 下午11:11:37
 * @version 1.0
 */
public class LockTest {
	
	public static void main(String[] args) {
		final BooleanLock booleanLock = new BooleanLock();
		Stream.of("T1", "T2", "T3", "T4").forEach(name -> 
				new Thread(()-> {
					try {
						Optional.of(Thread.currentThread().getName()+" is get the lock").ifPresent(System.out::println);
						booleanLock.lock(10L);
						work();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}catch (TimeOutException e) {
						Optional.of(Thread.currentThread().getName()+"time out").ifPresent(System.out::println);;
					}finally {
						// at any condition we should fee the lock
						booleanLock.unlock();
					}
				}, name).start());
//		booleanLock.unlock();
	}
	
	public static void work() throws InterruptedException {
		Optional.of(Thread.currentThread().getName() + " is working").ifPresent(System.out::println);
		Thread.sleep(2_000);
	}

}
