package org.university.software;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.university.hardware.*;
import org.university.people.*;
import java.io.Serializable;

public class Course implements Serializable {
	private String course_name;
	private Professor professor;
	private int course_number;
	private Department course_department;
	private Classroom classroom;
	private ArrayList<Person> student_roster= new ArrayList<>();
	private  ArrayList<Schedule> schedule= new ArrayList<>();
	
	
	
	
	public void setName(String course_name){
		this.course_name=course_name;
	}
	
	public String getName(){
		 return course_name;
	 }
	
	public void setCourseNumber(int course_number){
		this.course_number=course_number;
	}
	
	public int getCourseNumber(){
		return course_number;
	}
	
	public void setSchedule( int scheduleNum){
		
		this.schedule.add(new Schedule(scheduleNum));
		
	}
	
	public ArrayList<Schedule> getSchedule(){
		
		return schedule;
	}
	
	public void addStudentInRoster(Person newPerson){
		
		student_roster.add(newPerson);
	}
	
	public ArrayList<Person> getStudentRoster(){
		
		return student_roster;
	}
	

	
	public void setDepartment(Department course_department){
		
		this.course_department= course_department;
	}
	
	
	public Department getDepartment() {
		 return this.course_department;
	}
	
	public void setProfessor(Professor professor){
		
		this.professor= professor;	
	}
	
	
	public Professor getProfessor() {
		 return this.professor;
	}
	
	public String getCourseNickName(){
		
		return course_department.getDepartmentName()+ Integer.toString(course_number);
	}
	
	
	public Classroom getClassroom(){
		return classroom;
	}
	
	public void setRoomAssigned(Classroom aClassroom){
		
		if(!(aClassroom.detectConfict(this))){
			classroom=aClassroom;
			aClassroom.addCourse(this);
			
			for(Schedule schd:this.getSchedule()){
				schd.setCourse(this);
				aClassroom.addinSchedule(schd);
				}
			
		}
		else{

			for(int j=0; j<aClassroom.getSchedule().size();j++){ //
				
				for(int i=0; i< schedule.size();i++ ){//			
						
					if(aClassroom.getSchedule().get(j).getSchNum() == schedule.get(i).getSchNum()){
						
							System.out.print(schedule.get(i).getCourse().getCourseNickName()+" conflicts with "+
											aClassroom.getSchedule().get(j).getCourse().getCourseNickName());
							System.out.print(". Conflicting time slot "+aClassroom.getSchedule().get(j).getStringSchedule()+". ");
							
							System.out.println(
									schedule.get(i).getCourse().getCourseNickName()+
								" course cannot be added to "+aClassroom.getRoomNumber()+"'s Schedule.");					
					}
				}}
		}
	}
	
	

	public void  printSchedule(){
		
		 
		Collections.sort(schedule, new Comparator<Schedule>(){	//sort the course schedule vector by schedule number	
			public int compare(Schedule schd1,Schedule schd2){		
				return Integer.valueOf(schd1.getSchNum()).compareTo(schd2.getSchNum());}});



		for(Schedule schd: schedule)
				System.out.println(schd.getStringSchedule()+" "+this.classroom.getRoomNumber());
						 		 
		
		
	}
}
