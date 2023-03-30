package com.ui;

import java.time.LocalDate;
import java.util.Scanner;

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
		
		//call the method of dao layer to add employee to database and get the status
		//if everything is successful, show msg "Employee Added Successfully"
		//if any error, provide suitable msg
		
	}
	
	static void updateEmployeeUI(Scanner sc) {
		//Take input
		System.out.print("Enter Employee ID ");
		String eid = sc.next();
		
		System.out.print("Enter Employee Name ");
		String name = sc.next();
		
		System.out.print("Enter Employee Salary");
		double salary = sc.nextDouble();
		
		System.out.print("Enter Joining Date in YYYY-MM-DD Format");
		LocalDate joiningDate = LocalDate.parse(sc.next());
		
		//call the method of dao layer to update employee Details to database and get the status
		//if everything is successful, show msg "Employee Updated Successfully"
		//if any error, provide suitable msg
		
	}
	
	static void deleteEmployeeUI(Scanner sc) {
		//Take input
		System.out.print("Enter Employee ID ");
		String eid = sc.next();

		//call the method of dao layer to delete employee from database and get the status
		//if everything is successful, show msg "Employee Deleted Successfully"
		//if any error, provide suitable msg
		
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
				//viewAllEmployee(sc);
				break;
			case 3:
				updateEmployeeUI(sc);
				break;
			case 4:
				deleteEmployeeUI(sc);
				break;
			case 5:
				//searchEmployeeByJoiningDate(sc);
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
