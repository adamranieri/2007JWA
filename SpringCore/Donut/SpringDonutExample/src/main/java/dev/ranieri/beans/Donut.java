package dev.ranieri.beans;

public class Donut {
	
	private String name;
	private double cost;
	private Jelly jelly;// is a dependency for donut
	
	public Donut() {
		super();
	}

	public Donut(String name, double cost, Jelly jelly) {
		super();
		this.name = name;
		this.cost = cost;
		this.jelly = jelly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Jelly getJelly() {
		return jelly;
	}

	public void setJelly(Jelly jelly) {
		this.jelly = jelly;
	}

	@Override
	public String toString() {
		return "Donut [name=" + name + ", cost=" + cost + ", jelly=" + jelly + "]";
	}
	
	

}
