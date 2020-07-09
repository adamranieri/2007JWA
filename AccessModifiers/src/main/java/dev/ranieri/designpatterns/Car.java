package dev.ranieri.designpatterns;

import java.io.Serializable;

// POJO Plain old Java Object - (Every class that is not a bean)
// Java Bean - class with private fields, public getters and setters, and a no args constructor
public class Car {
	
	//private fields
	private String make;
	private String model;
	private int mpg;
	
	// no args constructor
	public Car() {
		super();
	}
		
	public Car(String make, String model, int mpg) {
		super();
		this.make = make;
		this.model = model;
		
		if(mpg<0) {
			this.mpg = 0;
		}else {
			this.mpg = mpg;
		}
		
	}

	// public getters and setters
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMpg() {
		return mpg;
	}
	public void setMpg(int mpg) {
		
		if(mpg<0) {
			this.mpg = 0;
		}else {
			this.mpg = mpg;
		}
		
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", mpg=" + mpg + "]";
	}
	
	

}
