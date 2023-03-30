package com.hrm.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.hrm.dao.DBUtils;
import com.hrm.dao.EmployeeDAO;
import com.hrm.dao.EmployeeDAOImpl;
import com.hrm.dto.Employee;
import com.hrm.dto.EmployeeImpl;
import com.hrm.exception.NoRecordFoundException;
import com.hrm.exception.SomethingWentWrongException;

public class UIMain {
	static void addEmployeeUI(Scanner sc) {
		//Take input
		System.out.print("Enter Employee ID ");
		String eid = sc.next();
		
		System.out.print("Enter Employee Name ");
		String name = sc.next();
		
		System.out.print("Enter Employee Salary");
		double salary = sc.nextDouble();
		
		System.out.print("Enter Joining Date in YYYY-MM-DD Format");
		LocalDate joiningDate = LocalDate.parse(sc.next());
		
		//create an object of DTO 
		Employee emp =  new EmployeeImpl(eid, name, salary, joiningDate);
		
		EmployeeDAO empDAO = new EmployeeDAOImpl();
		System.out.println("Employee added successfully");
		try {
			empDAO.addEmployee(emp);
		} catch (SomethingWentWrongException e) {
			e.printStackTrace();
		}
	}
	
	static void updateEmployeeUI(Scanner sc){
		//Take input
		System.out.print("Enter Employee ID ");
		String eid = sc.next();
		
		System.out.print("Enter Employee Name ");
		String name = sc.next();
		
		System.out.print("Enter Employee Salary");
		double salary = sc.nextDouble();
		
		System.out.print("Enter Joining Date in YYYY-MM-DD Format");
		LocalDate joiningDate = LocalDate.parse(sc.next());
		
		//create object of DTO
		Employee emp = new EmployeeImpl(eid, name, salary, joiningDate);
		
		//create object of DAO
		EmployeeDAO empDAO = new EmployeeDAOImpl();
		
		try {
			//call the update employee method
			empDAO.updateEmployee(emp);
			System.out.println("Employee Updated Successfully");
		} catch (SomethingWentWrongException e) {
			System.out.println(e);
		}
	}
	
	static void viewEmployeeUI() {
		//get an object of DAO
		EmployeeDAO empDAO= new EmployeeDAOImpl();
		try {
			List<Employee> empList = empDAO.getEmployeList();
			Consumer<Employee> consumer = emp -> System.out.println("Employee Id : "+emp.getEmpID()+" || name: "+emp.getName()
													+" || salary: "+emp.getSalary()
													+" || Joining Date: "+emp.getJoiningDate()); 
			
			empList.forEach(consumer);
			
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	static void deleteEmployeeUI(Scanner sc) {
		//Take input
		System.out.print("Enter Employee ID ");
		String eid = sc.next();
		
		//take object of dao
		EmployeeDAO empDAO = new EmployeeDAOImpl();
		
		try {
			//call the employee dlt method
			empDAO.deleteEmployee(eid);
			
			//print success msg
			System.out.println("Employee Deleted Successfully");
		} catch (SomethingWentWrongException e) {
			System.out.println(e);
		}
	}
	
	static void searchEmployeeByJoiningDate(Scanner sc) {
		//Take input
		System.out.print("Enter Start Date");
		LocalDate  startdate = LocalDate.parse(sc.next());
		
		System.out.print("Enter End Date");
		LocalDate endDate = LocalDate.parse(sc.next());
		
		EmployeeDAO empDAO = new EmployeeDAOImpl();
		
		try {
			
		List<Employee> list= empDAO.searchEmployeeByDateRange(startdate, endDate);
		
		list.forEach(emp -> System.out.println("Employee Id: "+emp.getEmpID()+
												" || Name: "+emp.getName()+							
												" || Salary: "+emp.getSalary()+
												" || JoiningDate: "+emp.getJoiningDate()));
		
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select from below");
		int choice = 0;
		
		do {
			System.out.println("1. Add Employee ");
			System.out.println("2. View Employees ");
			System.out.println("3. Update Employee ");
			System.out.println("4. Delete Employee ");
			System.out.println("5. Search Employee by Joining Date Range ");
			System.out.println("0. Exit ");
			System.out.print("Enter Selection ");
			
			choice = sc.nextInt();
			switch(choice){
			case 1:
				addEmployeeUI(sc);
				break;
			case 2:
				viewEmployeeUI();
				break;
			case 3:
				updateEmployeeUI(sc);
				break;
			case 4:
				deleteEmployeeUI(sc);
				break;
			case 5:
				searchEmployeeByJoiningDate(sc);
				break;
			case 0:
				System.out.println("Bye-Bye!! Thanks for Using our Services!!");
				break;
			default:
				System.out.println("Enter Valid Input");
			}

		}while(choice!=0);
		
		sc.close();
	}

	

	
}
