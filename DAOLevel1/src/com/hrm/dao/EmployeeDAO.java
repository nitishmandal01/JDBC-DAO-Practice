package com.hrm.dao;

import java.time.LocalDate;
import java.util.List;

import com.hrm.dto.Employee;
import com.hrm.exception.NoRecordFoundException;
import com.hrm.exception.SomethingWentWrongException;

public interface EmployeeDAO {

		public void addEmployee(Employee emp) throws SomethingWentWrongException;
		public void updateEmployee(Employee emp) throws SomethingWentWrongException;
		public void deleteEmployee(String empId) throws SomethingWentWrongException;
		public List<Employee> getEmployeList() throws SomethingWentWrongException, NoRecordFoundException;
		public List<Employee> searchEmployeeByDateRange(LocalDate startdate, LocalDate endDate) throws SomethingWentWrongException, NoRecordFoundException;
  
}
