package cn.laochou.concurrency.learn_two.extra;

/**
 * 	the calculate for tax
 * @author Administrator
 *
 */
public class TaxCalculator {
	
	private final double salary;
	private final double bouns;
	private final CalculateStrategy calculateStrategy;
	public TaxCalculator(double salary, double bouns, CalculateStrategy calculateStrategy) {
		this.salary = salary;
		this.bouns = bouns;
		this.calculateStrategy = calculateStrategy;
	}
	public double getSalary() {
		return salary;
	}
	public double getBouns() {
		return bouns;
	}
	public CalculateStrategy getCalculateStrategy() {
		return calculateStrategy;
	}
	
	public double getTax() {
		return calculateStrategy.getTax(salary, bouns);
	}
	
	

	

}
