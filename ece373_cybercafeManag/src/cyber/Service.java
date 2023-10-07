package cyber;



public class Service {
	private String name;
	private double price;
	
	public Service(){
	name = "unknown";
	price = 0.0;
	}

	public void setName(String aName){
		name = aName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPrice(double aPrice){
		price = aPrice;
	}
	
	public double getPrice(){
		return price;
	}
	
}
