package dev.ranieri.entities;

// Your entities are objects that are for storing information
// they contain very little to no logic
// they should be Java Beans
public class School {
	
	private int sId; // every entity should have a unique id to identify it
	
	private String name;	
	private int capacity;
	
	public School() {
		super();
	}
	
	public School(int sId, String name, int capacity) {
		super();
		this.sId = sId;
		this.name = name;
		this.capacity = capacity;
	}

	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "School [sId=" + sId + ", name=" + name + ", capacity=" + capacity + "]";
	}

}
