package cn.laochou.learn_four;
/** 
 * @author:Laochou
 * @date 2019年3月21日 下午9:39:09
 * @version 1.0
 */
public abstract class Observer {
	
	protected Subject subject;
	
	public Observer(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	public abstract void update();

}
