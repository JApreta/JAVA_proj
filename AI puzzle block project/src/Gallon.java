

public class Gallon {
	
	private int content;
	private int capacity;
	
	public Gallon(int cap){
		capacity=cap;
		content=0;
	}
	
	public Gallon(int cap, int cont){
		capacity=cap;
		content=cont;
	}
	
	public Gallon(){
		capacity=0;
		content=0;
	}

	public void setContent(int content){
		this.content=content;
		
	}
	public void setCapacity(int capacity){
		this.capacity=capacity;
		
	}
	
	public int getContent(){
		return this.content;
		
	}
	
	public int getCapacity(){
		return this.capacity;
		
	}
	
}
