package model;
import java.util.List;

public class SingleCheckBoxFeature extends Feature{

	private String name;
	private List<Option> optionList;
	
	public SingleCheckBoxFeature() {
		
	}
	
	public SingleCheckBoxFeature(List<Option> optionList) {
		this.optionList = optionList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Option> getOptionList() {
		return this.optionList;
	}
	
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	
	public String toString() {
		return "Single Check Box: " + this.name + "\n" + "Options: " + this.optionList.toString() + "\n";
	}
}
