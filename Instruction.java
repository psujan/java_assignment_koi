package ecbsystem;

import java.util.Arrays;
import java.util.HashMap;

/*
 *This class parse and validates instruction received from scanner input
 *
 * */
public class Instruction {
	HashMap<String , Object> contactInformation = new HashMap<String , Object>();
	
	public Instruction() {
		this.contactInformation.put("name" , "");
		this.contactInformation.put("birthday", "");
		this.contactInformation.put("phone", "");
		this.contactInformation.put("email", "");
		this.contactInformation.put("address", "");
	}
	
	
	public void handleAdd(String inst) {
		Boolean isValid = validateAddInstruction(inst);
		if(isValid) {
			PhoneBook pBook= new PhoneBook(
					(String)this.contactInformation.get("name"), 
					(String)this.contactInformation.get("birthday"), 
					(String)this.contactInformation.get("phone"),
					(String)this.contactInformation.get("email"),
					(String)this.contactInformation.get("address")
					);
			EcbController ecb=  new EcbController();
			ecb.addRecord(pBook);
		}
	}
	
	public  boolean validateAddInstruction(String inst) {
//		String wholeInstruction =  inst;
		String wholeInstructionArr[] = inst.split(" ", 2);
		
		// check if the first word of add instruction has add command
		if(!wholeInstructionArr[0].equals("add")) {
			System.out.println("Invalid Add Command (expected 'add') , Received " + wholeInstructionArr[0]);
			return false;
		}
		
		//check if the instruction has sufficient length
		if(wholeInstructionArr.length < 2) {
			System.out.println("Incomplete Add Instruction " + inst);
			return false;
		}
		
		//check if the instruction has valid parameters like name , date of birth
		String[] informationInstructionArr = wholeInstructionArr[1].split(";");
		if(informationInstructionArr.length < 1) {
			// it means after spliting with ";" there is missing information like name or dob
			System.out.println("Invalid or incomplete add instruction: " + inst);
			return false;
		}
		
		String name = null , birthday = null , email = "" , phone = "", address="";
		
		for(int i= 0 ; i< informationInstructionArr.length ; i++) {
			String[] informationSet  = informationInstructionArr[i].split(" " , 2);
//			System.out.println(Arrays.toString(informationSet));
			if(informationSet[0].isEmpty()) {
				System.out.println("Failure to parse command.Ivalid Command Received :"+ inst);
				return false;
			}
			System.out.println(informationSet[0]);
			if(informationSet[0].equals("name") || informationSet[0].equals("birthday") || informationSet[0].equals("phone") || informationSet[0].equals("email") || informationSet[0].equals("address")) {
				switch (informationSet[0]) {
					case "name":
						if(informationSet.length < 2) {
							System.out.println("No name in the instruction identified");
							return false;
						}
						name = informationSet[1];
						this.contactInformation.put("name" , name);
					case "birthday":
						if(informationSet.length < 2) {
							System.out.println("No birthday in the instruction identified");
							return false;
						}
						birthday = informationSet[1];
						this.contactInformation.put("birthday" , birthday);
						break;
					case "email":
						if(informationSet.length < 2) {
							System.out.println("No email in the instruction identified");
						}
						email = informationSet[1];
						this.contactInformation.put("email" , email);
						break;
					case "phone":
						if(informationSet.length < 2) {
							System.out.println("No phone number in the instruction identified");
						}
						phone = informationSet[1];
						System.out.println("Phone" + phone);
						this.contactInformation.put("phone" , phone);
						break;
					case "address":
						if(informationSet.length < 2) {
							System.out.println("No phone number in the instruction identified");
						}
						address = informationSet[1];
						this.contactInformation.put("address" , address);
						break;
						
					default:
						System.out.println(Arrays.toString(informationSet));
						System.out.println("Unrecognized Attribute Or Invalid Command");
						return false;
					
				}
			}
		}
		
		if(!name.isEmpty() && !birthday.isEmpty()) {
			return true;
		}
		
		System.out.println("Instruction do not have sufficient information. Name and birthday is required");
		return false;
		
	}
	
	public static void main(String[] args) {
//		validateAndAddInstruction("add name sujan poudel;birthday 18-07-1996;address 19 albert road strathfield");
//		validateAddInstruction("add something");
//		validateAddInstruction("add something");
//		validateAddInstruction("add name ; dob;");
		Instruction instn = new Instruction();
		Boolean isValid = instn.validateAddInstruction("add name sujan poudel;birthday 18-07-1996;address 19 albert road strathfield;phone 0426419217;email 20028844@koi.edu.au");
		System.out.println(isValid);
		System.out.println(instn.contactInformation);
	}
	
	
	
}
