package ecbsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import java.io.PrintWriter;

public class EcbController {
	private ArrayList<PhoneBook> contactRecords = new ArrayList<PhoneBook>();
	private ArrayList<PhoneBook> deletedRecords = new ArrayList<PhoneBook>();
	private ArrayList<PhoneBook> queryRecords  =  new ArrayList<PhoneBook>();
	
	public EcbController() {
		this.loadContactRecord();
		this.loadDeletedContactRecord();
	}
	
	/*
	 * read phone-book.txt file content
	 * read contact information and create phonebook object
	 * save phone book object to arraylist
	*/
	public void loadContactRecord() {
		Scanner fs = null;
		String name = "" , birthday="" , phone="", email="", address="";
		String line = null;
		try {
			File file = new File("/C:\\Users\\User\\eclipse-workspace\\javalab\\src\\ecbsystem\\phone-book.txt"); 
			fs = new Scanner(file);
			do {
				line = fs.nextLine();
				if(line == null) {
					break;
				}
				if(!line.isEmpty()) {
					for(int i = 0;  i< 5; i++) {
						String[] contactLineArr = line.split(" " , 2);
						if(contactLineArr.length > 0 && !contactLineArr[0].isEmpty()) {
							String key = contactLineArr[0];
							String value = contactLineArr[1];
							if(contactLineArr[1].isEmpty()) {
								value = "";
							}
							switch (key){
								case "name":
									name = value;
									break;
								case "phone":
									phone = value;
									break;
								case "email":
									email = value;
									break;
								case "birthday":
									birthday = value;
									break;
								case "address":
									address = value;
									break;
								default:
									break;
							}
						}
						line = fs.nextLine();
					}
					PhoneBook phoneBook  = new PhoneBook(name , phone , birthday , email , address);
					this.addRecord(phoneBook);
				}
			}while(fs.hasNextLine());			
		}catch(Exception e) {
			e.printStackTrace();
		}
		fs.close();
	}
	
	public void loadDeletedContactRecord() {
		Scanner delFile = null;
		String name = "" , birthday="" , phone="", email="", address="";
		String line = null;
		try {
			File file = new File("/C:\\Users\\User\\eclipse-workspace\\javalab\\src\\ecbsystem\\deleted-phone-book.txt"); 
			delFile = new Scanner(file);
			do {
				line = delFile.nextLine();
				if(line == null) {
					break;
				}
				if(!line.isEmpty()) {
					for(int i = 0;  i< 5; i++) {
						String[] contactLineArr = line.split(" " , 2);
						if(contactLineArr.length > 0 && !contactLineArr[0].isEmpty()) {
							String key = contactLineArr[0];
							String value = contactLineArr[1];
							if(contactLineArr[1].isEmpty()) {
								value = "";
							}
							switch (key){
								case "name":
									name = value;
									break;
								case "phone":
									phone = value;
									break;
								case "email":
									email = value;
									break;
								case "birthday":
									birthday = value;
									break;
								case "address":
									address = value;
									break;
								default:
									break;
							}
						}
						line = delFile.nextLine();
					}
					PhoneBook phoneBook  = new PhoneBook(name , phone , birthday , email , address);
					this.addDeletedRecord(phoneBook);
				}
			}while(delFile.hasNextLine());			
		}catch(Exception e) {
			e.printStackTrace();
		}
		delFile.close();
	}
	
	public ArrayList<PhoneBook>  getAll() {
		return contactRecords;
	}
	
	public void addRecord(PhoneBook phoneBook) {
		this.contactRecords.add(phoneBook);
	}
	
	public void addDeletedRecord(PhoneBook pb) {
		this.deletedRecords.add(pb);
	}
	
	
	public void updateRecord(PhoneBook phoneBook , int index) {
		this.contactRecords.get(index).setName(phoneBook.getName());
		this.contactRecords.get(index).setPhone(phoneBook.getPhone());
		this.contactRecords.get(index).setBirthday(phoneBook.getBirthday());
		this.contactRecords.get(index).setEmail(phoneBook.getEmail());
		this.contactRecords.get(index).setAddress(phoneBook.getAddress());
	}
	
	public void deleteRecord(String name , String birthday) {
		int i = 0;
		ArrayList<PhoneBook> tempRecords =  new ArrayList<PhoneBook>();
		for(PhoneBook pb : this.contactRecords) {
			if(pb.getName().equals(name) &&  pb.getBirthday().equals(birthday)) {
				this.deletedRecords.add(pb);
				i++;
			}else {
				tempRecords.add(pb);
			}
		}
		this.contactRecords = tempRecords;
		System.out.println("Found "+  i +" entries with name " + name+" and birthday " + birthday);
		Boolean msg = false;
		if(i > 0) { msg = true;}
		this.addDeletedRecordsToFile(msg);
		// save original phone book records by removing deleted entries
		this.addRecordsToFile("Saved" , false);
	}
	
	
	public void deleteRecordByName(String name) {
		int i = 0;
		ArrayList<PhoneBook> tempRecords =  new ArrayList<PhoneBook>();
		for(PhoneBook pb : this.contactRecords) {
			if(pb.getName().equals(name)) {
				this.deletedRecords.add(pb);
				i++;
			}else {
				tempRecords.add(pb);
			}
		}
		this.contactRecords = tempRecords;
		System.out.println("Found "+ i +" entries with name " + name);
		// save deleted records to new file
		Boolean msg = false;
		if(i > 0) { msg = true;}
		this.addDeletedRecordsToFile(msg);
		
		// save original phone book records by removing deleted entries
		this.addRecordsToFile("Saved" , false);
	}
	
	public void queryRecord() {
		
	}
	
	public void saveRecord() {
		
	}
	
	public void showRecord() {
		
	}
	
	public int checkForExistingRecord(PhoneBook pb) {
		int index = -1;
		for(PhoneBook phoneBook : this.contactRecords ) {
			if(phoneBook.getName().equals(pb.getName()) && phoneBook.getBirthday().equals(pb.getBirthday())) {
				index = this.contactRecords.indexOf(phoneBook);
				return index;
			}
		}
		return index;
	}
	
	public void addRecordsToFile(String action , Boolean showMessage) {
		try {
			File f = new File("/C:\\Users\\User\\eclipse-workspace\\javalab\\src\\ecbsystem\\phone-book.txt");
			f.createNewFile();
			PrintWriter pw =  new PrintWriter(f);
			for(PhoneBook pb : contactRecords) {
				pw.println("name " + pb.getName());
				pw.println("birthday " + pb.getBirthday());
				pw.println("phone " +pb.getPhone());
				pw.println("email " + pb.getEmail());
				pw.println("address " + pb.getAddress());
				pw.println();
			}
			pw.close();
			if(showMessage) {
				System.out.println("Record " + action + " to file successfully");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addDeletedRecordsToFile(Boolean showMessage) {
		try {
			File f = new File("/C:\\Users\\User\\eclipse-workspace\\javalab\\src\\ecbsystem\\deleted-phone-book.txt");
			f.createNewFile();
			PrintWriter pw =  new PrintWriter(f);
			for(PhoneBook pb : this.deletedRecords) {
				pw.println("name " + pb.getName());
				pw.println("birthday " + pb.getBirthday());
				pw.println("phone " +pb.getPhone());
				pw.println("email " + pb.getEmail());
				pw.println("address " + pb.getAddress());
				pw.println();
			}
			pw.close();
			if(showMessage) {
				System.out.println("Deleted Record saved to file successfully");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EcbController ecb =  new EcbController();
		System.out.println(ecb);
	}
}
