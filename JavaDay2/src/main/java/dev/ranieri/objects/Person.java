package dev.ranieri.objects;

// IF your class does not extend anything it extends object
public class Person extends Object {

	String name;
	int age;
	String profession;
	
	
	public Person(String name, int age, String profession) {
		super(); // building the parent which in this case is Object
		this.name = name;
		this.age = age;
		this.profession = profession;
	}
	
	@Override 
	// intent clarifying annotation. lets develops know that you mean to override this
	// prevents you from making a mistake
	public String toString() {
		return "name: " + this.name +" age: "+ this.age + " profession: " + this.profession;
	}
}
