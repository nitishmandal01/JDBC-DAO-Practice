package com.nitish.ui;

import java.util.Scanner;

public class UIMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Use CategoryUI, ProductUI, UserUI, OrderUI here
		System.out.println("Select Option to show");
		int choice = 0;
		
		do {
			System.out.println("1. Admin Login ");
			System.out.println("2. User Login ");
			System.out.println("3. User Registration ");
			System.out.println("0. Exit ");
			System.out.print("Enter Selection ");
			
			choice = sc.nextInt();
			switch(choice){
			case 1:
				adminLogin(sc);
				break;
			case 2:
				//viewEmployeeUI();
				break;
			case 3:
				//updateEmployeeUI(sc);
				break;
			case 0:
				System.out.println("Thank You!! Visit again!!");
				break;
			default:
				System.out.println("Enter Valid Input");
			}

		}while(choice!=0);
		
		sc.close();
	}

	static void adminLogin(Scanner sc) {
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			adminMenu(sc);
		}else {
				System.out.println("Invalid Username or Password");
		}
	}
	
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		
		do {
			displayAdminMenu();
			System.out.println("Enter Selection");
			 choice	= sc.nextInt();
			switch(choice){
			case 1:
				addCetegory(sc);
				break;
			case 2:
				//viewEmployeeUI();
				break;
			case 3:
				//updateEmployeeUI(sc);
				break;
			case 0:
				System.out.println("Bye-Bye Admin!!");
				break;
			default:
				System.out.println("Enter Valid Input");
			}

		}while(choice!=0);
		
		sc.close();
	}


	static void displayAdminMenu() {
		System.out.println("Select Any Button from Below ");
		System.out.println("0. LogOut Admin");
		System.out.println("1. Add new Category");
		System.out.println("2. View all products of a category");
		System.out.println("3. Add new products");
		System.out.println("4. View all products");
		System.out.println("5. View all Users");
		System.out.println("6. View all Orders");
	}
	
	
	
	
	
	
}
