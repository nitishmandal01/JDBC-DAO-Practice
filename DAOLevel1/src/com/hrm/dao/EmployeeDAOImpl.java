package com.hrm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hrm.dto.Employee;
import com.hrm.dto.EmployeeImpl;
import com.hrm.exception.NoRecordFoundException;
import com.hrm.exception.SomethingWentWrongException;

public class EmployeeDAOImpl implements EmployeeDAO{
	public void addEmployee(Employee emp) throws SomethingWentWrongException {
		//step1: get connection to database 
		Connection conn =null;
		try {
			conn = DBUtils.getConenctionTODatabase();
			
			String query = "INSERT INTO employee (eid, name, salary, joining_date) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, emp.getEmpID());
			ps.setString(2, emp.getName());
			ps.setDouble(3, emp.getSalary());
			ps.setDate(4,Date.valueOf(emp.getJoiningDate())); 
			
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SomethingWentWrongException("Unable to add students");
		}finally {
			try {
				DBUtils.closeConenction(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			}
		}

	@Override
	public void updateEmployee(Employee emp) throws SomethingWentWrongException {
		//step1: get connection to database 
		Connection conn =null;
		try {
			conn = DBUtils.getConenctionTODatabase();
			
			String query = "UPDATE employee SET name=?, salary =?, joining_date=? WHERE eid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setDate(3,Date.valueOf(emp.getJoiningDate())); 
			ps.setString(4, emp.getEmpID());			
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SomethingWentWrongException("Unable to update students");
		}finally {
			try {
				DBUtils.closeConenction(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			}
	}

	@Override
	public void deleteEmployee(String empId) throws SomethingWentWrongException {
		Connection conn=null;
		try {
			conn = DBUtils.getConenctionTODatabase();
			String query="DELETE FROM employee WHERE eid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,empId);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new SomethingWentWrongException("Cannot delete Employee");
		}finally {
			try {
				DBUtils.closeConenction(conn);
			} catch (SQLException e) {
			}
		}
		
		
	}

	@Override
	public List<Employee> getEmployeList() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		List<Employee> list =new ArrayList<>();
		try {
			conn = DBUtils.getConenctionTODatabase();
			String query= "SELECT eid, name, salary, joining_date FROM employee";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No Employee Found");
			}else {
				while(rs.next()) {
					list.add(new EmployeeImpl(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDate(4).toLocalDate()));
				}
			}
		} catch (ClassNotFoundException |SQLException e) {
			throw new SomethingWentWrongException("Unable to get the Employee Data!! Try again later");
		}finally {
			try {
				DBUtils.closeConenction(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return list;
	}

	@Override
	public List<Employee> searchEmployeeByDateRange(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		List<Employee> list =new ArrayList<>();
		try {
			conn = DBUtils.getConenctionTODatabase();
			String query= "SELECT eid, name, salary, joining_date FROM employee WHERE joining_date BETWEEN ? AND ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1,Date.valueOf(startDate));
			ps.setDate(2,Date.valueOf(endDate));

			ResultSet rs = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No Employee Found");
			}else {
				while(rs.next()) {
					list.add(new EmployeeImpl(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDate(4).toLocalDate()));
				}
			}
		} catch (ClassNotFoundException |SQLException e) {
			throw new SomethingWentWrongException("Unable to get the Employee Data!! Try again later");
		}finally {
			try {
				DBUtils.closeConenction(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return list;
	}

	



}

