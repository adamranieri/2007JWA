package dev.ranieri.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import dev.ranieri.beans.Donut;
import dev.ranieri.beans.Jelly;

@Component
@Configuration
public class DonutConfig {

	@Bean
	public Jelly blueBerryJelly() {
		Jelly j = new Jelly("Blueberry");
		return j;
	}
	
	@Bean
	public Jelly strawBerryJelly() {
		Jelly j = new Jelly("Strawberry");
		return j;
	}
	
	@Bean
	public Donut blueberryBlastDonut() {
		// by calling the method in this class to create a blueberry donut we are 
		// using th3 blueberry jelly bean
		// Dependency injection via the constructor
		Donut d = new Donut("Blueberry Blast", 2.25,this.blueBerryJelly());
		return d;
	}
	
	@Bean
	public Donut strawBerrySwirlDonut() {
		Donut d = new Donut();
		// dependency injection via the setters
		d.setName("Strawberry Swirl");
		d.setCost(2.45);
		d.setJelly(this.strawBerryJelly());
		return d;
		
	}
	
}
