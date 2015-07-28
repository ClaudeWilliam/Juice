package com.gbros.modle;

public class Student {
	private String id;
	private String name;
	private String grade;
	private String location;
	
	public Student(String id, String name, String grade, String location) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.location = location;
	}
	
	public Student() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
