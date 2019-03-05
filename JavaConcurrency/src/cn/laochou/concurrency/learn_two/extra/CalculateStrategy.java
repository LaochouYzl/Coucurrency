package cn.laochou.concurrency.learn_two.extra;

/**
 * 		the strategy for calculate, this mean if you need some logistic code
 * 	you can create new class to implement the interface not modify your previous code.
 * @author Administrator
 *
 */
public interface CalculateStrategy {
	
	double getTax(double salary, double bouns);

}
