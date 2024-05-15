package ecbsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import java.io.PrintWriter;

public class EcbController {
	private ArrayList<PhoneBook> contactRecords = new ArrayList<PhoneBook>();
	
	public EcbController() {
		this.loadContactRecord();
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
	
	public ArrayList<PhoneBook>  getAll() {
		return contactRecords;
	}
	
	public void addRecord(PhoneBook phoneBook) {
		this.contactRecords.add(phoneBook);
	}
	
	public void deleteRecord() {
		
	}
	
	public void queryRecord() {
		
	}
	
	public void saveRecord() {
		
	}
	
	public void showRecord() {
		
	}
	
	public void addRecordsToFile() {
		System.out.println("Adding Records");
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EcbController ecb =  new EcbController();
		System.out.println(ecb);
	}
}
