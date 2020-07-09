package dev.ranieri.stringvbuilder;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringVsStringBuilder {

	@Test
	public void String() {
		
		Long start = System.nanoTime();
		String a = "a";
		
		for( int i = 0; i <1000; i ++) {
			a = a.concat("b");
		}
		
		Long end = System.nanoTime();
		
		System.out.println("String time : " + (end -start));
	}
	
	@Test
	public void StringBuilder() {
		
		Long start = System.nanoTime();
		StringBuilder a = new StringBuilder("a");
		
		for( int i = 0; i <1000; i ++) {
			a.append("b");
		}
		
		Long end = System.nanoTime();
		
		System.out.println("SB time : " + (end -start));
	}


}
