package model;


import java.util.List;



public class SingleCheckBoxFeature extends Feature{

	private String size;
	private List<Option> optionList;
	
	public SingleCheckBoxFeature(){
		super();
	}
	
	public SingleCheckBoxFeature(String name, List<Option> optionList) {
		super(name);
		this.optionList = optionList;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
		
	public List<Option> getOptionList() {
		return optionList;
	}
	
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Options: " + optionList.toString();
	}
}