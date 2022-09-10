package org.university.hardware;

import java.util.ArrayList;

import org.university.people.*;
import org.university.software.*;
import java.io.Serializable;

public class Department implements Serializable{
	private String  name;
	ArrayList <Course> courseList= new ArrayList<Course>();
	ArrayList <Student> studentList= new ArrayList<Student>();
	ArrayList <Professor> professorList= new ArrayList<Professor>();
	ArrayList <Staff> staffList= new ArrayList<Staff>();
	
	
	public void setDepartmentName(String name){
		
		this.name= name;
	}
	
	public String getDepartmentName(){
		return name;
	}
	
	public void addCourse(Course newCourse){
		courseList.add(newCourse);
		newCourse.setDepartment(this);
		
	}
	
	
	public void addStudent(Student newStudent){
		
		studentList.add(newStudent);
		newStudent.setDepartment(this);
		
	}
	
	public void addStaff(Staff newStaff){
		
		staffList.add(newStaff);
		newStaff.setDepartment(this);
		
	}
	
	public void addProfessor(Professor newProfessor){
		
		professorList.add(newProfessor);
		newProfessor.setDepartment(this);
		
	}
	
	public ArrayList <Student> getStudentList(){
		return studentList;
	}
	
	public ArrayList <Professor> getProfessorList(){
		return professorList;
	}
	
	public ArrayList <Course> getCourseList(){
		return courseList;
	}
	
	public ArrayList <Staff> getStaffList(){
		return staffList;
	}
	
	public void printStudentList(){
		
		for(Student std: studentList)
			System.out.println(std.getName());
		
	}
	public void printStaffList(){
		
		for(Staff stf: staffList)
			System.out.println(stf.getName());
		
	}
	public void printProfessorList(){
		
		for(Professor prf: professorList)
			System.out.println(prf.getName());
	}
	public void printCourseList(){
		
		for(Course crs: courseList)
			System.out.println(crs.getCourseNickName());
	}
	
	
	
}


