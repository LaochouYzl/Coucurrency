package cn.laochou.concurrency.learn_two.extra;

public class Main {
	
	public static void main(String[] args) {
		// the strategy for entrepreneur
		// in jdk8 has a character is lambda now please let me to use it to simplify code
		CalculateStrategy calculateStrategy = new EntrepreneurTaxCalculator();
		TaxCalculator taxCalculator = new TaxCalculator(100000d, 2000d, calculateStrategy);
		TaxCalculator userCalculator = new TaxCalculator(5000d, 2000d, (s, d)-> s * 0.2 + d * 0.1);
		System.out.println("the tax for entrepreneur is : " + taxCalculator.getTax());
		System.out.println("the tax for common user is : " + userCalculator.getTax());
		
	}

}
