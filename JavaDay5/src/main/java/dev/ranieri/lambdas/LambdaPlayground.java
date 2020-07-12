package dev.ranieri.lambdas;

public class LambdaPlayground {

	// instance variable
	Calculable add = (a,b) ->{
		return a +b;
	};
	
	public static void main(String[] args) {
		
		LambdaPlayground l = new LambdaPlayground();
		
		System.out.println(l.add.calculate(87.4, 85.4));
		
	}
}
