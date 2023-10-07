package cyber;



public class MembershipPlan {
	
	private int iD;
	private String name;
	private int totalHours;
	private double totalPrice;
	private double price_Hour;
	private int validationDays;
	private boolean isActive;
	
	public MembershipPlan(){
		iD=0;
		name="unkwon";
		totalHours=0;
		totalPrice=0.0;
		price_Hour=0.0;
		validationDays=0;
		isActive=false;
		
	}
	
	public void setID(int iD){
		this.iD=iD;
	}
	
	public int getID(){
		return iD;
	}

	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	
	public void setTotalHours( int totalHours){
		this.totalHours=totalHours;
	}
	public int getTotalHours(){
		return totalHours;
	}
	public void setTotalPrice(double totalPrice){
		this.totalPrice=totalPrice;
	}
	
	public double getTotalPrice(){
		return totalPrice;
	}
	
	public void setPricePerHour(double price_Hour){
		this.price_Hour=price_Hour;
	}
	public double getPricePerHour(){
		return price_Hour;
	}
	
	public void setValidationDays(int validationDays){
		this.validationDays=validationDays;
	}
	public int getValidationDays(){
		return validationDays;
	}
	
	public void setStatus( boolean isActive){
		this.isActive=isActive;
	}
	boolean getStatus(){
		return isActive;
	}

}
