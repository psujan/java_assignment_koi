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
		if(!isValid) {
			System.out.println("Failed to add record from provided instruction :" + inst);
			return;
		}
		
		PhoneBook pBook= new PhoneBook(
				(String)this.contactInformation.get("name"), 
				(String)this.contactInformation.get("birthday"), 
				(String)this.contactInformation.get("phone"),
				(String)this.contactInformation.get("email"),
				(String)this.contactInformation.get("address")
				);
		// check for existing record
		// if no record found then add a new one else update existing one
		EcbController ecb=  new EcbController();
		int foundIndex = ecb.checkForExistingRecord(pBook);
		if(foundIndex >= 0) {
			System.out.println("There exist similar record with matching name and birthday");
			ecb.updateRecord(pBook, foundIndex);
			//save updated record to file
			ecb.addRecordsToFile("Updated", true);
		}else {
			ecb.addRecord(pBook);
			//save new record to file
			ecb.addRecordsToFile("Added", true);
		}
		
	}
	
	/*
	 * parse scanner instruction in string format
	 * check if the instruction is valid and contain sufficient parameter like name & birthday
	 * */
	
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
		
		String name = "" , birthday = "" , email = "" , phone = "", address="";
		
		for(int i= 0 ; i< informationInstructionArr.length ; i++) {
			String[] informationSet  = informationInstructionArr[i].split(" " , 2);
//			System.out.println(Arrays.toString(informationSet));
			if(informationSet[0].isEmpty()) {
				System.out.println("Failure to parse command.Ivalid Command Received :"+ inst);
				return false;
			}
			if(informationSet[0].equals("name") || informationSet[0].equals("birthday") || informationSet[0].equals("phone") || informationSet[0].equals("email") || informationSet[0].equals("address")) {
				switch (informationSet[0]) {
					case "name":
						if(informationSet.length < 2) {
							System.out.println("No name in the instruction identified");
							return false;
						}
						name = informationSet[1];
						this.contactInformation.put("name" , name);
						break;
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
							break;
						}
						email = informationSet[1];
						this.contactInformation.put("email" , email);
						break;
					case "phone":
						if(informationSet.length < 2) {
							System.out.println("No phone number in the instruction identified");
							break;
						}
						phone = informationSet[1];
						this.contactInformation.put("phone" , phone);
						break;
					case "address":
						if(informationSet.length < 2) {
							System.out.println("No address in the instruction identified");
							break;
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
		
//		System.out.println("Name:" + name + "Birthday:" + birthday);
		if(!name.isEmpty() && !birthday.isEmpty()) {
			return true;
		}
		
		System.out.println("Instruction do not have sufficient information. Name and birthday is required");
		return false;
		
	}

	public void handleQuery(String queryInstruction) {
		EcbController ecb=  new EcbController();
		 String[] parts = queryInstruction.split(" ", 3);
	        if (parts.length == 3 && parts[0].equalsIgnoreCase("query")) {
	            String field = parts[1];
	            String value = parts[2];
	            ecb.queryRecord(field, value);
	        } else {
	            System.out.println("Invalid query instruction format.");
	        }
	}

	public void handleDelete(String inst) {
		this.resetContactInformation();
		Boolean isValid = this.validateDeleteInstruction(inst);
		if(!isValid) {
			System.out.println("Unable to proceed with delete instruction");
			return;
		}
		EcbController ecb =  new EcbController();
		String name = (String)this.contactInformation.get("name");
		String dob = (String)this.contactInformation.get("birthday");
		if(dob.isEmpty()) {
			//delete all records having the name
			ecb.deleteRecordByName(name);
			return;
		}
		
		ecb.deleteRecord(name , dob);
		
		//delete record having name and birthday
	}
	
	public boolean validateDeleteInstruction(String inst) {
		this.resetContactInformation();
		String wholeInstructionArr[] = inst.split(" ", 2);
		
		// check if the first word of delete instruction has delete command
		if(!wholeInstructionArr[0].equals("delete")) {
			System.out.println("Invalid Delete Command (expected 'delete') , Received " + wholeInstructionArr[0]);
			return false;
		}
		
		//check if the instruction has sufficient length
		if(wholeInstructionArr.length < 2) {
			System.out.println("Incomplete Delete Instruction Received:  " + inst);
			return false;
		}
		
		//check for name and dateofbirth values by spliting
		//if array has one item , it is name
		String[] delInstructionArray = wholeInstructionArr[1].split(";");
		String name = delInstructionArray[0];
		if(name.isEmpty()) {
			System.out.println("Name is expected in delete instruction");
			return false;
		}
		String birthday = "";
		if(delInstructionArray.length >= 2) {
			birthday =  delInstructionArray[1];
		}
		this.contactInformation.put("name", name);
		this.contactInformation.put("birthday", birthday);
		return true;
	}
	
	public void resetContactInformation() {
		this.contactInformation.put("name" , "");
		this.contactInformation.put("phone", "");
		this.contactInformation.put("birthday", "");
		this.contactInformation.put("email", "");
		this.contactInformation.put("address", "");
	}
	
	
	public static void main(String[] args) {
		Instruction instn = new Instruction();
//		instn.handleAdd("add name sujann poudel;birthday 18-07-1996;address 19 albert road strathfield;phone 0426419217;email 20028844@koi.edu.au");
//		System.out.println(instn.contactInformation);
		instn.handleDelete("delete sujan poudel");
	}
	
	
	
}
