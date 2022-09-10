package org.university.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

import org.university.hardware.*;
import org.university.people.*;

public class University implements Serializable {
	
	
	protected  ArrayList <Department> departmentList= new ArrayList<>();
	protected  ArrayList <Classroom> classroomList= new ArrayList<>();	
	protected String allInfo="\n";

	public University(){}
	
	public University(University u){
		this.departmentList=u.departmentList;
		this.classroomList=u.classroomList;
	}
	
	public void addDepartment(Department newDepartment){
		
		departmentList.add(newDepartment);
	}
	
	public void addClassRoom(Classroom newClassroom){
		
		classroomList.add(newClassroom);
	}

	public void printStudentList(){
	

		for(Department dep:departmentList){
		
			for(Student std: dep.getStudentList()){
				System.out.println(std.getName());
			
			}
			}
	}

	public void printStaffList(){	
	
		for(Department dep:departmentList){
		
			for(Staff stf: dep.getStaffList())
				System.out.println(stf.getName());
		}
		
	}
	
	public void printProfessorList(){
		
		for(Department dep:departmentList){
			
			System.out.println("The professor list for department "+dep.getDepartmentName());
		
			for(Professor prf: dep.getProfessorList())
				System.out.println(prf.getName());
			System.out.println();
			}
	}
	
	public void printCourseList(){
		
		for(Department dep:departmentList){
		
			System.out.println("The course list for department "+dep.getDepartmentName());
			for(Course crs: dep.getCourseList())
				System.out.println(crs.getCourseNickName());
			System.out.println();
	}
	}
	
	public void printDepartmentList(){
		
		System.out.println("List of departments:");
		
		
		for(Department dep:departmentList){	
			
			System.out.println(dep.getDepartmentName());
			
					}
		System.out.println();
		
	}
	
	public void printClassroomList(){
		
		System.out.println("Classroom list:");
		
		
		for(Classroom clr: classroomList){
			System.out.println(clr.getRoomNumber());
		}
		System.out.println();
	}
	
	public void printClassroomSchd(){
		int i=0;
		
		for(Classroom clr:classroomList){
			i++;
			System.out.println("The schedule for classroom "+clr.getRoomNumber());
		
			clr.printSchedule();
			if(i<classroomList.size() ){
				System.out.println();
				
			}
		}
	}
	
	public void printProfessorSchd(ArrayList <Professor> professorList){
		
		System.out.println("Printing Professor schedules:\n");
		
		for(Professor prof: professorList){
			System.out.println("The schedule for Prof. "+prof.getName()+":");
			prof.printSchedule();
			System.out.println();
		}
	}
	
	public void printStudentSchd(ArrayList <Student> studentList){
		
		System.out.println("Printing Student Schedules:\n");
		
		for(Student std: studentList){
			System.out.println("The schedule for Student "+std.getName()+":");
			std.printSchedule();
			System.out.println();
		}
	}
	
	
	public void printStaffSchd(ArrayList <Staff> staffList){
		
		System.out.println("Printing Staff Schedules:\n");
		
		for(Staff stf: staffList){
			System.out.println("The schedule for Employee "+stf.getName()+":");
			stf.printSchedule();
			System.out.println("\n");
			
			System.out.print("Staff : "+stf.getName()+" earns $");
			System.out.printf("%.2f",stf.earns());
			System.out.println(" this month\n");
		}
	}
	
	
	public void printCourseRoster(Department dp){
		int i=0;
		System.out.println("The rosters for courses offered by "+dp.getDepartmentName()+"\n");
		for(Course cr:dp.getCourseList()){
			i++;
			System.out.println("The roster for course "+cr.getCourseNickName());
			for(Person prs: cr.getStudentRoster()){
				System.out.println(prs.getName());
			}
			if(i<dp.getCourseList().size() )
			System.out.println();
		}
		
	}
	
	public static void  saveData(University univ) {
		FileOutputStream  fileOut = null;
		ObjectOutputStream objOut=null;
		
		try{
			fileOut= new FileOutputStream ("university.ser");
			objOut= new ObjectOutputStream(fileOut);
			objOut.writeObject(univ);
			objOut.close();
			fileOut.close();
			
			
		} 
		
		catch( IOException i){
			i.printStackTrace();
			
		}
	}
	
	public static University  loadData() {
		FileInputStream  fileInp = null;
		ObjectInputStream objInp=null;
		University uni =null;
		
		try{
			
			fileInp= new FileInputStream ("university.ser");
			objInp= new ObjectInputStream(fileInp);
			uni=(University)objInp.readObject();
			objInp.close();
			fileInp.close();
		}
		
		catch(IOException i){
			
			i.printStackTrace();
		}
		
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		
		return  uni;
	}	
	
	
	public void printAll(){
		
		System.out.println();
		printDepartmentList();
		
		printClassroomList();
		printProfessorList();
		printCourseList();
		printClassroomSchd();
		
		
		for(Department dep:departmentList){
			System.out.println();
			System.out.println("Department "+dep.getDepartmentName());
			System.out.println();
			printProfessorSchd(dep.getProfessorList());
			
			printStudentSchd(dep.getStudentList());
			
			printStaffSchd(dep.getStaffList());
			
			printCourseRoster(dep);
			
			}
					
	}
	
	public String getAllInfo(){
		
		
		allInfo=allInfo+"List of departments:"+"\n";
		
		for(Department dep:departmentList)		
			allInfo=allInfo+dep.getDepartmentName()+"\n";
					
		allInfo=allInfo+"\n";
		
		allInfo=allInfo+"Classroom list:"+"\n";
		
		for(Classroom clr: classroomList)
			allInfo=allInfo+clr.getRoomNumber()+"\n";
			
			allInfo=allInfo+"\n";
			
		for(Department dep:departmentList){
				
				allInfo=allInfo+"The professor list for department "+dep.getDepartmentName()+"\n";
				
				for(Professor prf: dep.getProfessorList())
					allInfo=allInfo+prf.getName()+"\n";
					
				allInfo=allInfo+"\n";
				}	
			
		for(Department dep:departmentList){
			
			allInfo=allInfo+"The course list for department "+dep.getDepartmentName()+"\n";
			for(Course crs: dep.getCourseList())
				allInfo=allInfo+crs.getCourseNickName()+"\n";
			allInfo=allInfo+"\n";
	}	
		

		
		for(Classroom clr:classroomList){
			
			allInfo=allInfo+"The schedule for classroom "+clr.getRoomNumber()+"\n";
					sort(clr.getSchedule());
					
					for(Schedule schd: clr.getSchedule()){
						
						allInfo=allInfo+schd.getStringSchedule()+" "+
								 schd.getCourse().getCourseNickName()+" "+
											 schd.getCourse().getName()+"\n";
					
					}
					allInfo=allInfo+"\n";
			
			}
		
		for(Department dep:departmentList){
			
			allInfo=allInfo+"\n";
			allInfo=allInfo+"Department "+dep.getDepartmentName()+"\n";
			allInfo=allInfo+"\n";
			
			allInfo=allInfo+"Printing Professor schedules:\n";
			
		
			
			for(Professor prof: dep.getProfessorList()){
				allInfo=allInfo+"The schedule for Prof. "+prof.getName()+":\n";
				
				sort(prof.getSchedule());
				
				for(Schedule schd: prof.getSchedule()){				
					allInfo=allInfo+schd.getStringSchedule()+" "+
							 schd.getCourse().getCourseNickName()+" "+
										 schd.getCourse().getName()+"\n";				
			}
				allInfo=allInfo+"\n";
			}
			//printStudentSchd(dep.getStudentList());
				
			allInfo=allInfo+"Printing Student schedules:\n";
			
			for(Student std: dep.getStudentList()){
				allInfo=allInfo+"The schedule for Student "+std.getName()+":\n";
				
				sort(std.getSchedule());
				
				for(Schedule schd: std.getSchedule()){				
					allInfo=allInfo+schd.getStringSchedule()+" "+
							 schd.getCourse().getCourseNickName()+" "+
										 schd.getCourse().getName()+"\n";				
			}
				allInfo=allInfo+"\n";
			}
				
				
			
			//printStaffSchd(dep.getStaffList());
			
			allInfo=allInfo+"Printing Staff schedules:\n";
			
			for(Staff stf: dep.getStaffList()){
				allInfo=allInfo+"The schedule for Student "+stf.getName()+":\n";
				
				sort(stf.getSchedule());
				
				for(Schedule schd: stf.getSchedule()){				
					allInfo=allInfo+schd.getStringSchedule()+" "+
							 schd.getCourse().getCourseNickName()+" "+
										 schd.getCourse().getName()+"\n";				
			}
				allInfo=allInfo+"\n";
			}
			
			//printCourseRoster(dep);
			
			allInfo=allInfo+"The rosters for courses offered by "+dep.getDepartmentName()+"\n\n";
			for(Course cr:dep.getCourseList()){
				
				allInfo=allInfo+"The roster for course "+cr.getCourseNickName()+"\n";
				for(Person prs: cr.getStudentRoster()){
					allInfo=allInfo+prs.getName()+"\n";
				}
				allInfo=allInfo+"\n";
			}
			
	}
		return allInfo;
	}

	
	public void sort(ArrayList<Schedule> schedule){
	
	Collections.sort(schedule, new Comparator<Schedule>()//sort the  classroom schedule vector by schedule number
			
			{public int compare(Schedule schd1,Schedule schd2){
				
				return Integer.valueOf(schd1.getSchNum()).compareTo(schd2.getSchNum());}});
	
}
	
}
