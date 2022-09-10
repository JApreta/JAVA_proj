package org.university.people;


//import org.university.hardware.Department;



public abstract class Employee extends Person {
	
	
	private double payRate;
	//private Department departemt;

	
	public Employee(){
		super();
		payRate=0;
	}

	public void raise(double percent){
		
		payRate += (payRate*percent)/100 ;
	}
	
	
	public void setPayRate( double payRate){
		this.payRate= payRate;
	}
	
	public double getPayRate(){
		
		return payRate;
	}
	
	public abstract double earns();
	

	/*public void setDepartment(Department departemt){
	
	this.departemt= departemt;}


	public Department getDepartment() {
	 return this.departemt;}*/


	

		
}
