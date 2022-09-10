package org.university.software;
import java.io.Serializable;
public class Schedule implements Serializable{
	
	
	 
	
	private String str_schedule;
	
	//private int day;
	//private int slot;
	private int schNum;
	private Course course;
	
	public Schedule(int sch){
		
		 String[]  week  ={"Mon","Tue","Wed","Thu","Fri"};
		String[]  slt={"8:00am to 9:15am",
					   "9:30am to 10:45am",
					   "11:00am to 12:15pm" ,
					   "12:30pm to 1:45pm",
					   "2:00pm to 3:15pm",
					   "3:30pm to 4:45pm"};
		
		schNum = sch;
		//day= sch/100;
		//slot=sch%10;
		this.str_schedule = week[(sch/100)-1]+ " "+ slt[(sch%10)-1];
		
		
	}

	public void setCourse(Course newCourse){
		
		this.course=newCourse;
	}
	
	public Course getCourse(){
		return course;
	}
	
	/*public int getDay(){
		return day;
	}
	
	public int getSlot(){
		return slot;
	}*/
	
	public int getSchNum(){
		return schNum;
	}
	
	public String getStringSchedule(){
		return str_schedule;
	}
	
}
