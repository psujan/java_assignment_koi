package ecbsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	Instruction inst =  new Instruction();

	public Menu() {
		
	}
	
	public void initMenu() {
		try {
			int choice;
//			Scanner sc = new Scanner(System.in);
			System.out.println("=========WELCOME TO ECB SYSTEM=====");
			System.out.println("Add Record:                       1");
			System.out.println("Delete Record:                    2");
			System.out.println("Query Record:                     3");
			System.out.println("Show Queried Record:              4");
			System.out.println("Show All Phone Record:            5");
			System.out.println("====================================");
			
			System.out.println("Please enter your choice:");
			choice = sc.nextInt();
			System.out.println("Your Choice : " + choice);
//			sc.close();
			this.handleChoiceActions(choice);
		}catch(Exception e) {
			System.out.println("System error occured at runtime. This may be due to invalid choice value");
			System.exit(0);
//			e.printStackTrace();
		}
	}
	
	public void handleChoiceActions(int ch) {
		switch(ch) {
			case 1:
				this.getAddInstruction();
				break;
			case 2:
				this.getDeleteInsturction();
				break;
			case 3:
				this.getQueryInstruction();
				break;
			case 4:
				this.showAllQueryRecord();
				System.out.println("Please enter your choice:");
				int chc = sc.nextInt();
				this.handleChoiceActions(chc);
				break;
			case 5:
				this.showAllRecord();
				System.out.println("Please enter your choice:");
				int choice = sc.nextInt();
				this.handleChoiceActions(choice);
			default:
				System.out.println("Your choice doesn't match to any desired options . Please try by providing a valid choice number");
				System.exit(0);
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
		String queryInstruction;
		Scanner querySc = new Scanner(System.in);
		System.out.println("Please provide a valid query instruction: ");
		queryInstruction = querySc.nextLine();
		System.out.println("Query  Received :" + queryInstruction);	
		querySc.close();
		inst.handleQuery(queryInstruction);
	}
	
	public void showAllQueryRecord() {
		EcbController ecb = new EcbController();
		System.out.println("Saved Query Records with queries.");
		ecb.loadAllQueryRecords();
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
