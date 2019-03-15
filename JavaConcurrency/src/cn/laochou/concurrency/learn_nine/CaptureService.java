package cn.laochou.concurrency.learn_nine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/** 
 * @author:Laochou
 * @date 2019年3月10日 下午4:02:00
 * @version 1.0
 * the service of capture
 */
public class CaptureService {
	
	private final static LinkedList<Control> controls = new LinkedList<Control>();
	
	public static void main(String[] args) {
		List<Thread> workers = new ArrayList<Thread>();
		Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream().map(CaptureService::createThread)
			.forEach(t -> {
				t.start();
				workers.add(t);
			});
		workers.stream().forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Optional.of("All of capture workers has finished work").ifPresent(System.out::println);
	}
	
	public static Thread createThread(String name) {
		return new Thread(()->{
			synchronized (controls) {
				// at current , we only allow five workers to work
				while(controls.size() > 5) {
					try {
						controls.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Optional.of("The worker ["+Thread.currentThread().getName()+"] begin captrue data").ifPresent(System.out::println);
				controls.addLast(new Control());
			}
			try {
				// assume every work to work ten seconds
				Optional.of("The worker ["+Thread.currentThread().getName()+"] is working").ifPresent(System.out::println);
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// if current work finished his work, we need remove him in controls and inform others can work
			synchronized (controls) {
				Optional.of("The worker ["+Thread.currentThread().getName()+"] end captrue data").ifPresent(System.out::println);
				controls.removeFirst();
				controls.notifyAll();
			}
//			Optional.of("The worker ["+Thread.currentThread().getName()+"] begin captrue data").ifPresent(System.out::println);
		}, name);
	}

	private static class Control{
		
	}
}
