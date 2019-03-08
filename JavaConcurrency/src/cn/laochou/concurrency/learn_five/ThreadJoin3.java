package cn.laochou.concurrency.learn_five;
/** 
 * @author:Laochou
 * @date 2019年3月6日 下午11:03:55
 * @version 1.0
 */
public class ThreadJoin3 {

}

class CaptrueRunnable implements Runnable{
	
	private String machineName;
	
	public CaptrueRunnable(String machineName) {
		this.machineName = machineName;
	}

	@Override
	public void run() {
		// do the really capture data!
		
	}
	
	public String getResult() {
		return machineName + "finished";
	}
	
}