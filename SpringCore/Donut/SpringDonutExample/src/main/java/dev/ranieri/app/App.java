package dev.ranieri.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.ranieri.beans.Box;
import dev.ranieri.beans.Donut;
import dev.ranieri.configs.AppConfig;
import dev.ranieri.configs.DonutConfig;

public class App {
	
	public static void main(String[] args) {
		// This is the only time in the main application I will use the new keyword
		// I will requests beans through this application context
		ApplicationContext ac = new AnnotationConfigApplicationContext(DonutConfig.class);
		
//		Box low = ac.getBean("lowValueBox",Box.class);
//		Box low2 = ac.getBean("lowValueBox",Box.class);
//		Box hi = ac.getBean("highValueBox",Box.class);
//		Box hi2 = ac.getBean("highValueBox",Box.class);
//		
//		System.out.println(hi);
//		System.out.println(hi2);
//		System.out.println(low);
//		System.out.println(low2);
		
		Donut strawDonut = ac.getBean("strawBerrySwirlDonut",Donut.class);
		Donut blueDonut = ac.getBean("blueberryBlastDonut",Donut.class);
		
		System.out.println(strawDonut);
		System.out.println(blueDonut);
	}

}
