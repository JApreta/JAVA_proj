package org.university.people;

import org.university.software.Course;
import org.university.software.Schedule;

public class Professor extends Employee {

	
	public Professor(){
		super();
		
	}
	
	
	public double earns(){
		return 200* getPayRate();
	}
	
	
	public void addCourse(Course aCourse){
		
		
		if (!(detectConflict(aCourse))){//if there is no conflict in the professor schedule
			
			if(aCourse.getProfessor() == null){//if the course to be assigned does not have a prof
				course_list.add(aCourse);//add the course in the prof list of courses
				aCourse.setProfessor(this);//set the prof as teacher for this class
			
				for(Schedule schd:aCourse.getSchedule()){
					schd.setCourse(aCourse);//add the course on the prof schedule
					this.addinSchedule(schd);}// add the course schedule in the prof schedule
				}
			
			else{
				
				System.out.println("The professor cannot be assigned to this course because professor "+
						aCourse.getProfessor().getName()+" is already assigned to the course "+aCourse.getName()+".");		
			}
			}
		
		
	}
	
	
	
}
