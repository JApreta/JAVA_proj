package org.university.people;

//import org.university.hardware.*;
import org.university.software.*;


public class Student extends Person {
	
	//private Department student_Departemt;
	private int requiredCredits;
	private int completedUnits;
	
	public Student(){
		
		super();
	requiredCredits=0;
	completedUnits=0;	
	
	}
	
	
	/*public void setDepartment(Department student_Departemt){
		
		this.student_Departemt= student_Departemt;
		
	}
	
	public Department getDepartment() {
		 return this.student_Departemt;
	}*/
	
	 public void setCompletedUnits(int completedUnits){
		 this.completedUnits=completedUnits;
	 }
	
	 public int getCompletedUnits(){
		 return completedUnits;
	 }
	 
	 
	public void setRequiredCredits(int requiredCredits){
		this.requiredCredits=requiredCredits;
	}
	
	public int getRequiredCredits(){
		return requiredCredits;
	}
	
	public int requiredToGraduate(){
		 
		 return requiredCredits-completedUnits;
	 }

	public void dropCourse(Course aCourse){
		
		boolean flag=false;
				
		for(Course crs:course_list){
			
			if(crs.getCourseNickName().equals(aCourse.getCourseNickName())){
				flag=true;
				course_list.remove(crs);//remove course from student's list of courses
				crs.getStudentRoster().remove(this);//remove student from the course roster
				for(Schedule schd:aCourse.getSchedule()){
					getSchedule().remove(schd);//remove course from the student's schedule
					
					}				
				
				break;
			}		
		}
		
		if(!flag){
			
			System.out.println("The course "+aCourse.getCourseNickName()+
					" could not be dropped because "+getName()+" is not enrolled in "+aCourse.getCourseNickName()+".");
			
		}
			
	}
	
	
	public void addCourse(Course aCourse){
		
		
		if (!(detectConflict(aCourse))){
			course_list.add(aCourse);//add course on the student list of courses
			aCourse.addStudentInRoster(this);//add student on the course roster
			
			for(Schedule schd:aCourse.getSchedule()){
				schd.setCourse(aCourse);//add course in the schedule
				this.addinSchedule(schd);//add course schedule in the student schedule
				}
				
			}
		
	
	}
	

}
