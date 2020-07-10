package dev.ranieri.generics;

public class GenericPlayground {
	
	public static void main(String[] args) {
		
		
		MyAwesomeList<Integer> nums = new MyAwesomeList<Integer>();
		
		nums.addToList(89);
		nums.addToList(40);;
		
		
		MyAwesomeList<String> strings = new MyAwesomeList<String>();
		strings.addToList("gsdgfsf");
		strings.addToList("gsdgfsgf");
		
		strings.printAll();
		
	}

}
