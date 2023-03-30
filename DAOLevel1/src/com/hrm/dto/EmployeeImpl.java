package com.hrm.dto;

import java.time.LocalDate;

public class EmployeeImpl implements Employee{
	private String empID;
	private String name;
	private double salary;
	private LocalDate date;
	public EmployeeImpl(String empID, String name, double salary, LocalDate date) {
		super();
		this.empID = empID;
		this.name = name;
		this.salary = salary;
		this.date = date;
	}
	@Override
	public String getEmpID() {
		return empID;
	}
	
	@Override
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public double getSalary() {
		return salary;
	}
	
	@Override
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public LocalDate getJoiningDate() {
		return date;
	}
	
	@Override
	public void setJoiningDate(LocalDate date) {
		this.date = date;
	}
	
	
}
