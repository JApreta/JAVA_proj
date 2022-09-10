package org.university.people;

import org.university.software.Course;
import org.university.software.Schedule;

public class Staff extends Employee{

	private double hoursWorked;
	
	public Staff(){
		super();
		hoursWorked=0;
		
	}
	
	public void setMonthlyHours(double hoursWorked){
		this.hoursWorked=hoursWorked;
	}
	
	public double getHoursWorked(){
		return hoursWorked;
	}
	
	public double earns(){
		return hoursWorked* getPayRate();
	}
	
	
	
	public void addCourse(Course aCourse){
		
			
			if(!(course_list.isEmpty())){
				System.out.println(course_list.get(0).getCourseNickName()+" is removed from "+getName()+"'s schedule(Staff can only take one class at a time). "+
						aCourse.getCourseNickName()+" has been added to "+getName()+"'s Schedule.");
				getSchedule().get(0).getCourse().getStudentRoster().remove(this);//remove the staff from the previous course roster
				getSchedule().clear();//clear staff schedule
					
					}
					
				course_list.add(0, aCourse);//add the new course on the staff schedule
				aCourse.addStudentInRoster(this);//add staff in the new course roster
				
				for(Schedule schd:aCourse.getSchedule()){
					schd.setCourse(aCourse);//add the new course in the staff schedule
					this.addinSchedule(schd);}//add the course schedule in the staff schedule
				}
			
			
			
		}
	
	
	

