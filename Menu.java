package ecbsystem;

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
		System.out.println("Show All Record:                  5");
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
		
	}
	
	public void getSaveInstruction() {
		
	}
	
	public void getQueryInstruction() {
		
	}
}
