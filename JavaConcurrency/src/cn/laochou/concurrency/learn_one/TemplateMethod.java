package cn.laochou.concurrency.learn_one;

public class TemplateMethod {
	
	public final void print(String message) {
		System.out.println("$$$$$$$$$");
		realPrint(message);
		System.out.println("$$$$$$$$$");
	}
	
	// the method of template
	protected void realPrint(String message) {
		
	}
	
	public static void main(String[] args) {
		TemplateMethod templateMethod = new TemplateMethod() {
			protected void realPrint(String message) {
				System.out.println("+"+message+"+");
			}
		};
		templateMethod.print("Hello China");
	}

}
