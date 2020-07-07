package dev.ranieri.collections;


public class Person {
	
	String name;
	int age;
	int height;
	
	public Person(String name, int age, int height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + "]";
	}

}
