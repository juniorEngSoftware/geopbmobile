package model;



public class TextFeature extends Feature{

	private String content;

	public TextFeature(){
		super();
	}
	
	public TextFeature(String name, String content) {
		super(name);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content;
	}
}
