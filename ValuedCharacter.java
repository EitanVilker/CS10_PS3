
public class ValuedCharacter {
	protected String name;
	protected int frequency;


	public  ValuedCharacter(String name, int frequency){
		this.name = name;
		this.frequency = frequency;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}

