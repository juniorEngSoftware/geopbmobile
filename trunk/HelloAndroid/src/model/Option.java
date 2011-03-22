package model;

public class Option {

	private String name;
	
	public Option(String name) {
		this.name = name;
	}
	
	public Option() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Name: " + name;
	}
	
}
