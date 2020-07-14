package dev.noah.entities;

public class Task {
	private int id;
	private String name;
	private boolean isComplete;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(int id, String name, boolean isComplete) {
		super();
		this.id = id;
		this.name = name;
		this.isComplete = isComplete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", isComplete=" + isComplete + "]";
	}
}
