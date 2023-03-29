package com.studentExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DBConnectionExample {
	
	static Connection getConnectionToDatabase() throws ClassNotFoundException, SQLException {
		//Step1: load the Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Step2:
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		return DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));		
	}
	
	
	static void closeConenction(Connection conn) throws SQLException {
		if(conn!=null) {
			conn.close();
		}
	}
	
	static void addStudent(Scanner sc) throws ClassNotFoundException, SQLException {
		//create connection
		Connection conn = getConnectionToDatabase();
		
		//get an object of statement
		Statement st = conn.createStatement();
		
		//code to take input
		System.out.print("Enter student's roll number ");
		String roll = sc.next();

		System.out.print("Enter student's name ");
		String name = sc.next();
		
		System.out.print("Enter student's cgpa ");
		double cgpa = sc.nextDouble();
		
		System.out.print("Enter student's Date of Birth in 'YYYY-MM-DD' formet ");
		LocalDate dob = LocalDate.parse(sc.next());
		
		//form the query
		String query = "INSERT INTO student VALUES ('"+roll + "','"+name +"',"+cgpa + ",'"+ dob+"')";
		
		if(st.executeUpdate(query)>0) {
			System.out.println("Studnets added successfully");
		}else {
			System.out.println("Can't add Studnet");
		}
		
		closeConenction(conn);
	}
	
	static void updateStudent(Scanner sc) throws ClassNotFoundException, SQLException {
		//create connection
		Connection conn = getConnectionToDatabase();
		
		//get an object of statement
		Statement st = conn.createStatement();
		
		//code to take input
		System.out.print("Enter student's roll number ");
		String roll = sc.next();

		System.out.print("Enter student's name ");
		String name = sc.next();
		
		System.out.print("Enter student's cgpa ");
		double cgpa = sc.nextDouble();
		
		System.out.print("Enter student's Date of Birth in 'YYYY-MM-DD' formet ");
		LocalDate dob = LocalDate.parse(sc.next());
		
		//form the query
		String query = " UPDATE student SET name='"+name+"', cgpa="+cgpa+", dob='"+dob+"' WHERE rollNo = '"+roll+"' ";
		
		if(st.executeUpdate(query)>0) {
			System.out.println("Studnets updated successfully");
		}else {
			System.out.println("Can't update Studnet");
		}
		
		closeConenction(conn);
	}
	
	static void deleteStudent(Scanner sc) throws ClassNotFoundException, SQLException {
		//get connection
		Connection conn = getConnectionToDatabase();
		
		//create statement object
		Statement st =  conn.createStatement();
		
		//take input from user
		System.out.print("Give rollNo of student that you want to delete ");
		String roll = sc.next();
		
		//write query
		String query = "DELETE FROM student WHERE rollNo = '"+roll+"'";
		
		//fire query by st.executeUpdate();
		if(st.executeUpdate(query)>0) {
			System.out.println("Student Deleted Successfully");
		}else {
			System.out.println("Cannot delete Student");
		}
		
		//close connection
		conn.close();
		//close scanner object
	}
	
	static void printAllStudents() throws ClassNotFoundException, SQLException {
		//get connection
		Connection conn = getConnectionToDatabase();
		//create  Statement object
		Statement st = conn.createStatement();
		
		//write query
		String query = "SELECT * FROM student";

//		st.executeQuery(query)
		ResultSet rs = st.executeQuery(query);
		
		if(!rs.isBeforeFirst() && rs.getRow()==0) {
			//we're here means result set is empty
			System.out.println("No data to show here");
		}else {
			while(rs.next()) {
				System.out.println("Roll No: "+rs.getString(1)
								+" || Name: "+rs.getString(2)
								+" || CGPA: "+rs.getDouble(3)
								+" || DOB: "+rs.getDate(4));
			}
		}
		
		closeConenction(conn);
	}
	
	static void searchStudentByName(Scanner sc) throws ClassNotFoundException, SQLException {
		//get connection
		Connection conn = getConnectionToDatabase();
		//create  Statement object
		Statement st = conn.createStatement();
		
		//take input
		System.out.print("Enter Name to search ");
		String name = sc.next();
		//write query
		String query = "SELECT * FROM student WHERE name LIKE '%"+name+"%' ";

//		st.executeQuery(query)
		ResultSet rs = st.executeQuery(query);
		
		if(!rs.isBeforeFirst() && rs.getRow()==0) {
			//we're here means result set is empty
			System.out.println("No data to show here");
		}else {
			while(rs.next()) {
				System.out.println("Roll No: "+rs.getString(1)
								+" || Name: "+rs.getString(2)
								+" || CGPA: "+rs.getDouble(3)
								+" || DOB: "+rs.getDate(4));
			}
		}
		
		closeConenction(conn);
	}
	
	
	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Scanner sc = new Scanner(System.in);
	
	//create Main menu
	
	System.out.println("Select from below");
	
	int choice = 0;
	
	do {
		System.out.println("1. Add student ");
		System.out.println("2. Update student Details ");
		System.out.println("3. Delete student ");
		System.out.println("4. Show all students ");
		System.out.println("5. Search Student by name ");
		System.out.println("0. Exit ");
		System.out.println();
		System.out.print("Enter Selection ");
		
		choice = sc.nextInt();
		switch(choice){
		case 1:
			addStudent(sc);
			System.out.println();
			break;
		case 2:
			updateStudent(sc);
			System.out.println();
			break;
		case 3:
			deleteStudent(sc);
			System.out.println();
			break;
		case 4:
			printAllStudents();
			System.out.println();
			break;
		case 5:
			searchStudentByName(sc);
			System.out.println();
			break;
		case 0:
			System.out.println("Thanks for Using our Services");
			break;
		default:
			System.out.println("Enter Valid Input");
		}

	}while(choice!=0);

	//close scanner object
	sc.close();
}
}
