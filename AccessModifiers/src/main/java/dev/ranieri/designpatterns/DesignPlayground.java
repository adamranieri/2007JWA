package dev.ranieri.designpatterns;

public class DesignPlayground {
	
	public static void main(String[] args) {
		
		
		// what I do not want
		
		Thing a = Thing.getThingInstance();
		Thing b = Thing.getThingInstance();
		Thing c = Thing.getThingInstance();

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
	}

}
