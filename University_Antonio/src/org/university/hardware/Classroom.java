package org.university.hardware;
import org.university.software.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Classroom implements Serializable{
	
	private String roomNumber;
	private ArrayList<Course> courseList= new ArrayList<>();
	private  ArrayList<Schedule> schedule = new ArrayList<>() ;
	
	public void setRoomNumber(String roomNumber ){
		
		this.roomNumber=roomNumber;
	}

	public String getRoomNumber(){
		
		return roomNumber;
	}
	
	public void  addCourse(Course aCourse){
		
		courseList.add(aCourse);
		
	}
	
	public void addinSchedule(Schedule newItem){
		schedule.add(newItem);
		
	}
	
	public ArrayList<Schedule> getSchedule(){
		return schedule;
		
	}
	
	public boolean detectConfict(Course aCourse){
		
		for(Schedule schd: schedule){
						
				for(Schedule schd1: aCourse.getSchedule() ){
					if(schd.getSchNum()==schd1.getSchNum())
						return true;
				}
			}
		
		
		return false;
		
	}
	
	public void printSchedule(){
		
		//StringBuffer st = null;
		
		Collections.sort(schedule, new Comparator<Schedule>()//sort the  classroom schedule vector by schedule number
		
		{public int compare(Schedule schd1,Schedule schd2){
			
			return Integer.valueOf(schd1.getSchNum()).compareTo(schd2.getSchNum());}});
		
		for(Schedule schd: schedule){
			
			System.out.println(schd.getStringSchedule()+" "+
					 schd.getCourse().getCourseNickName()+" "+
								 schd.getCourse().getName());
		
		}
		
		
	}
	
	
}
