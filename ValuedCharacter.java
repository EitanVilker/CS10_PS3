
public class ValuedCharacter {
	protected Character name;
	protected Integer frequency;


	public  ValuedCharacter(Character name, Integer frequency){
		this.name = name;
		this.frequency = frequency;
	}
	
	public Character getName() {
		return name;
	}
	
	public void setName(Character name) {
		this.name = name;
	}
	
	public Integer getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
}

