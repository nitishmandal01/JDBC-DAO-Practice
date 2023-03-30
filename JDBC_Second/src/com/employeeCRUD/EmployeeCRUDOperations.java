package com.employeeCRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeCRUDOperations {

	static Connection getConnectionToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		
		return DriverManager.getConnection(rb.getString("url"),rb.getString("username"),
				rb.getString("password"));
	}
	
	static void closeConnection(Connection conn) throws SQLException {
		if(conn!=null)
			conn.close();
	}
	
	static void addEmployee(Scanner sc) {
		Connection conn = null;
		
		try {
			//step1:- Make connection
			conn = getConnectionToDatabase();
			
			//step 2:- form the query
			String query =  "INSERT INTO employee ( eid ,name ,Salary, joining_date) VALUES (?, ?, ?, ?)";
			
			//step 03 - Take an object of Prepared Statement
			PreparedStatement ps = conn.prepareStatement(query);
			
			//Steps 3 - take input
			System.out.print("Enter Employee ID ");
			String eid = sc.next();
			
			System.out.print("Enter Employee Name ");
			String name = sc.next();
			
			System.out.print("Enter Employee Salary");
			double salary = sc.nextDouble();
			
			System.out.print("Enter Joining Date in YYYY-MM-DD Format");
			LocalDate joiningDate = LocalDate.parse(sc.next());
			
			//set Value to placeholder of Query
			ps.setString(1, eid);
			ps.setString(2, name);
			ps.setDouble(3, salary);
			ps.setDate(4,Date.valueOf(joiningDate));
					
			System.out.println(ps);

			//execute query
			if(ps.executeUpdate()>0) {
				System.out.println("Employee Added Successfully");
			}else {
				System.out.println("Unable to add Employee");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	static void updateEmployee(Scanner sc) {
		Connection conn = null;
		
		try {
			//step1:- Make connection
			conn = getConnectionToDatabase();
			
			//step 2:- form the query
			String query =  "UPDATE employee SET name = ? ,Salary = ?, joining_date = ? WHERE eid=?";			
			//step 03 - Take an object of Prepared Statement
			PreparedStatement ps = conn.prepareStatement(query);
			
			//Steps 3 - take input
			System.out.print("Enter Employee ID ");
			String eid = sc.next();
			
			System.out.print("Enter Employee Name ");
			String name = sc.next();
			
			System.out.print("Enter Employee Salary");
			double salary = sc.nextDouble();
			
			System.out.print("Enter Joining Date in YYYY-MM-DD Format");
			LocalDate joiningDate = LocalDate.parse(sc.next());
			
			//set Value to placeholder of Query
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setDate(3,Date.valueOf(joiningDate));
			ps.setString(4, eid);
	
			//execute query
			if(ps.executeUpdate()>0) {
				System.out.println("Employee Updateded Successfully");
			}else {
				System.out.println("Unable to Update Employee");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	static void deleteEmployee(Scanner sc) {
		Connection conn = null;
		
		try {
			//step1:- Make connection
			conn = getConnectionToDatabase();
			
			//step 2:- form the query
			String query =  "DELETE employee FROM employee WHERE eid=?";			
			//step 03 - Take an object of Prepared Statement
			PreparedStatement ps = conn.prepareStatement(query);
			
			//Steps 3 - take input
			System.out.print("Enter Employee ID ");
			String eid = sc.next();
			
			//set Value to placeholder of Query
			ps.setString(1, eid);

			//execute query
			if(ps.executeUpdate()>0) {
				System.out.println("Employee Deleted Successfully");
			}else {
				System.out.println("Unable to Delete Employee");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	static void viewAllEmployee(Scanner sc) {
		Connection conn = null;
		
		try {
			//step1:- Make connection
			conn = getConnectionToDatabase();
			
			//step 2:- form the query
			String query =  "SELECT eid ,name ,Salary,joining_date FROM employee";			
			//step 03 - Take an object of Prepared Statement
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst() && rs.getRow()==0) {
				System.out.println("No Empoyee Found");
			}else {
				while(rs.next()){
					System.out.println("Employee ID: "+rs.getString(1)
					+" || Name: "+rs.getString(2)
					+" || Salary: "+rs.getString(3)
					+" || Joining Date: "+rs.getString(4));
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	static void searchEmployeeByJoiningDate(Scanner sc) {
		Connection conn = null;
		
		try {
			//step1:- Make connection
			conn = getConnectionToDatabase();
			
			//step 2:- form the query
			String query =  "SELECT eid ,name ,Salary,joining_date FROM employee WHERE joining_date BETWEEN ? AND ?";			
			//step 03 - Take an object of Prepared Statement
			PreparedStatement ps = conn.prepareStatement(query);
			
			//take input from user
			System.out.println("Enter Start Date in YYYY-MM-DD Formet");
			Date startDate = Date.valueOf(LocalDate.parse(sc.next()));
			
			System.out.println("Enter Start Date in YYYY-MM-DD Formet");
			Date endDate = Date.valueOf(LocalDate.parse(sc.next())) ;
			
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst() && rs.getRow()==0) {
				System.out.println("No Empoyee Found");
			}else {
				while(rs.next()){
					System.out.println("Employee ID: "+rs.getString(1)
					+" || Name: "+rs.getString(2)
					+" || Salary: "+rs.getString(3)
					+" || Joining Date: "+rs.getString(4));
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				addEmployee(sc);
				break;
			case 2:
				viewAllEmployee(sc);
				System.out.println();
				break;
			case 3:
				updateEmployee(sc);
				System.out.println();
				break;
			case 4:
				deleteEmployee(sc);
				System.out.println();
				break;
			case 5:
				searchEmployeeByJoiningDate(sc);
				System.out.println();
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
