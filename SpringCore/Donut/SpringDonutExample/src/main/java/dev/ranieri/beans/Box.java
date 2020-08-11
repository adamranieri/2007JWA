package dev.ranieri.beans;

public class Box {
	
	private int value;

	public Box(int value) {
		super();
		this.value = value;
	}

	public Box() {
		super();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

//	@Override
//	public String toString() {
//		return "Box [value=" + value + "]";
//	}


}
