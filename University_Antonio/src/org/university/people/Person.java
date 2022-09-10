package org.university.people;
import org.university.hardware.Department;
import  org.university.software.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;

public abstract class Person implements Serializable {
	
	private String name;
	private  ArrayList<Schedule> schedule = new ArrayList<>() ;
	protected ArrayList<Course>course_list = new ArrayList<> ();
	private Department departemt;
	
	
	
	public abstract void addCourse(Course aCourse);
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	
	public ArrayList<Schedule> getSchedule(){
		return schedule;
		
	}
	
	public void addinSchedule(Schedule newItem){
		schedule.add(newItem);
		
	}
	
	public ArrayList<Course> getCourseList(){
		return course_list;}
	
	public void setDepartment(Department departemt){
		
		this.departemt= departemt;}


		public Department getDepartment() {
		 return this.departemt;}

	
	
	public boolean detectConflict(Course aCourse){
		
		int flag=0;
		
		for(int j=0; j<aCourse.getSchedule().size();j++){ 
			
			for(int i=0; i< schedule.size();i++ ){
			
				if(aCourse.getSchedule().get(j).getSchNum() == schedule.get(i).getSchNum())
					flag++;
			}
		
		}
		if(flag!=0){
			for(int j=0; j<aCourse.getSchedule().size();j++){ //for the sched of the new course
				
				for(int i=0; i< course_list.size();i++ ){//for all course in the list
				
					for(int k =0; k<course_list.get(i).getSchedule().size();k++){
					
						if(aCourse.getSchedule().get(j).getSchNum() == course_list.get(i).getSchedule().get(k).getSchNum()){
					
							System.out.print(
							aCourse.getCourseNickName()
							+" course cannot be added to "+ getName()+"'s Schedule"+". ");
							System.out.print(
							aCourse.getCourseNickName()+
							" conflicts with "+course_list.get(i).getCourseNickName()+". ");
					
							System.out.println("Conflicting time slot is "+course_list.get(i).getSchedule().get(k).getStringSchedule()+".");				
					
					}}}}
			
		 return true;
		 
		}
		
		else
			return false;
	 }

	public void printSchedule(){
		
		Collections.sort(schedule, new Comparator<Schedule>()//sort the person schedule vector by schedule number
				
				{public int compare(Schedule schd1,Schedule schd2){
					
					return Integer.valueOf(schd1.getSchNum()).compareTo(schd2.getSchNum());}});
			
		for(Schedule schd: schedule){
			System.out.println(schd.getStringSchedule()+" "+
					 schd.getCourse().getCourseNickName()+" "+
								 schd.getCourse().getName());
			
		}
		
		
	}
	
}
