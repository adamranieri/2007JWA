package dev.ranieri.equals;

public class Apple implements Comparable<Apple> {
	
	private String type;
	private int mass;
	
	
	public Apple(String type, int mass) {
		super();
		this.type = type;
		this.mass = mass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	@Override
	public String toString() {
		return "Apple [type=" + type + ", mass=" + mass + "]";
	}

	// The Hashcode of an object is a numerical value
	// It represents the state of the object
	// It does not give you any information about the state of the object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mass;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		Apple apple = (Apple) obj;
		
		if(this.type.equals(apple.type)) {
			return true;
		}else {
			return false;
		}

	}

	public int compareTo(Apple apple) {
		
		if(this.mass < apple.mass) {
			return -1;
		}
		if(this.mass > apple.mass) {
			return 1;
		}
		// 0 for if they are the same	
		return 0;
	}
	
	

}
