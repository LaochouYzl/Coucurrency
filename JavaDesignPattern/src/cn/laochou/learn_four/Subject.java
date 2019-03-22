package cn.laochou.learn_four;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author:Laochou
 * @date 2019年3月21日 下午8:14:32
 * @version 1.0
 */
public class Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();

	private int state;
	
	public int getState() {
		return this.state;
	}
	
	public Subject setState(int state) {
		if(state == this.state) {
			return this;
		}
		this.state = state;
		this.notifyAllObserver();
		return this;
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyAllObserver() {
		observers.stream().forEach(Observer::update);
	}
	
	
	
}
