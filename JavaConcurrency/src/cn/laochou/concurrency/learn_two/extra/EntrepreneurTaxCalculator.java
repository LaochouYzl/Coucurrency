package cn.laochou.concurrency.learn_two.extra;

/**
 * 	because the world has many people, and has many different kinds of people, 
 * so has many strategy for our tax calculator
 * @author Administrator
 *
 */
public class EntrepreneurTaxCalculator implements CalculateStrategy{
	
	// for entrepreneur, they must has the tax of highly, because they has so much money
	private static final double SALAY_RATE = 0.4;
	private static final double BOUNS_RATE = 0.3;

	@Override
	public double getTax(double salary, double bouns) {
		return salary * SALAY_RATE + BOUNS_RATE * bouns;
	}
	
}
