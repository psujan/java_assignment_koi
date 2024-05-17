package ecbsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	Instruction inst =  new Instruction();

	public Menu() {
		
	}
	
	public void initMenu() {
		int choice;
//		Scanner sc = new Scanner(System.in);
		System.out.println("=========WELCOME TO ECB SYSTEM=====");
		System.out.println("Add Record:                       1");
		System.out.println("Delete Record:                    2");
		System.out.println("Query Record:                     3");
		System.out.println("Save Record:                      4");
		System.out.println("Show Phone Record:                5");
		System.out.println("Show Deleted Phone Record:        6");
		System.out.println("====================================");
		
		System.out.println("Please enter your choice:");
		choice = sc.nextInt();
		System.out.println("Your Choice : " + choice);
//		sc.close();
		this.handleChoiceActions(choice);
	}
	
	public void handleChoiceActions(int ch) {
		switch(ch) {
			case 1:
				this.getAddInstruction();
				break;
			case 2:
				this.getDeleteInsturction();
				break;
			case 5:
				this.showAllRecord();
				System.out.println("Please enter your choice:");
				int choice = sc.nextInt();
				this.handleChoiceActions(choice);
			default:
				break;
		}
	}
	
	public void getAddInstruction() {
		String addInstruction;
		Scanner addSc = new Scanner(System.in);
		System.out.println("Please provide a valid add instruction: ");
		addInstruction = addSc.nextLine();
		System.out.println("Instruction  Received :" + addInstruction);	
		addSc.close();
		inst.handleAdd(addInstruction);
	}
	
	public void getDeleteInsturction() {
		String delInstruction;
		Scanner delSc = new Scanner(System.in);
		System.out.println("Please provide a valid delete instruction: ");
		delInstruction = delSc.nextLine();
		System.out.println("Instruction  Received :" + delInstruction);	
		delSc.close();
		inst.handleDelete(delInstruction);
	}
	
	public void getSaveInstruction() {
		
	}
	
	public void getQueryInstruction() {
		
	}
	
	public void showAllRecord() {
		EcbController ecb= new EcbController();
		ArrayList<PhoneBook> records = ecb.getAll();
		System.out.printf("%-25s %-25s %-25s %-25s%-25s\n" , "Name" , "Phone" , "Birthday" , "Email" ,"Address");
		for(PhoneBook pb : records) {
			System.out.printf("%-25s %-25s %-25s %-25s%-25s\n" , pb.getName() , pb.getPhone() , pb.getBirthday() , pb.getEmail() , pb.getAddress());
		}
	}
}
