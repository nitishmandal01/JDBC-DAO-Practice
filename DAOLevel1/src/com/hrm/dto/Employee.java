package com.hrm.dto;

import java.time.LocalDate;

public interface Employee {
	
	public String getEmpID();
	public void setEmpID(String empID) ;
	
	public String getName();
	public void setName(String name);
	
	public double getSalary();
	public void setSalary(double salary);
	
	public LocalDate getJoiningDate();
	public void setJoiningDate(LocalDate date);
	
}
	
