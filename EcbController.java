
package ecbsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import java.io.PrintWriter;

public class EcbController {
	private ArrayList<PhoneBook> contactRecords = new ArrayList<PhoneBook>();
	private ArrayList<PhoneBook> deletedRecords = new ArrayList<PhoneBook>();	
	private ArrayList<QueryBook> queryRecords = new ArrayList<QueryBook>();

	
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

	public void queryRecord(String field, String value) {
        boolean found = false;
        for (PhoneBook record : contactRecords) {
            boolean match = false;
            switch (field.toLowerCase()) {
                case "name":
                    match = record.getName().equalsIgnoreCase(value);
                    break;
                case "birthday":
                    match = record.getBirthday().equals(value);
                    break;
                case "phone":
                    match = record.getPhone().equals(value);
                    break;
                default:
                    System.out.println("Invalid query field: " + field);
                    return;
            }
            if (match) {
               
                String lineToPrepend = "-----query " + field + " " + value + " -----";
                QueryBook queryBook = new QueryBook(lineToPrepend, record.getName(), record.getBirthday(), record.getPhone(), record.getEmail(), record.getAddress());
                queryRecords.add(queryBook);
                System.out.printf("checking saved data" + queryRecords);
                addQueryRecordToFile();
                
                found = true;
            }
        }
        if (!found) {
            System.out.println("No records found for the query: " + field + " " + value);
        }
    }

	public void addQueryRecordToFile() {
		System.out.println("Query Records \n");
		  String filePath = "/C:\\\\\\\\Users\\\\\\\\User\\\\\\\\eclipse-workspace\\\\\\\\javalab\\\\\\\\src\\\\\\\\ecbsystem\\\\\\\\query-book.txt";
		  try (FileWriter writer = new FileWriter(filePath, true)) {
	            for (QueryBook queryRecord : queryRecords) {
	                writer.write(queryRecord.toString() + "\n\n");
	            }
	            System.out.println("Data written to the file successfully.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file: " + e.getMessage());
	            e.printStackTrace();
	        }
	}
	public void loadAllQueryRecords() {
//		String line = null;
		try ( Scanner fs = new Scanner(new File("/C:\\\\Users\\\\User\\\\eclipse-workspace\\\\javalab\\\\src\\\\ecbsystem\\\\query-book.txt"))) {
		        String query = "", name = "", birthday = "", phone = "", email = "", address = "";

		        while (fs.hasNextLine()) {
		             String line = fs.nextLine().trim();
		            if (line.startsWith("Query: -----")) {
		                query = line.substring(7).trim();
		            } else if (line.startsWith("Name: ")) {
		                name = line.substring(6).trim();
		            } else if (line.startsWith("Birthday: ")) {
		                birthday = line.substring(10).trim();
		            } else if (line.startsWith("Email: ")) {
		                email = line.substring(7).trim();
		            } else if (line.startsWith("Phone: ")) {
		                phone = line.substring(7).trim();
		            } else if (line.startsWith("Address: ")) {
		                address = line.substring(9).trim();
		            } else if (line.isEmpty()) {
		            
		            	 if (!query.isEmpty()) {
		                     QueryBook queryBook = new QueryBook(query, name, birthday, phone, email, address);
		                     queryRecords.add(queryBook);
		                     // Reset fields for next record
		                     query = name = birthday = phone = email = address = "";
		                 }
		                
		            }
		        }
		        // Add the last record if it doesn't end with an empty line
		        if (!query.isEmpty() || !name.isEmpty() || !birthday.isEmpty() || !phone.isEmpty() || !email.isEmpty() || !address.isEmpty()) {
		            QueryBook queryBook = new QueryBook(query, name, birthday, phone, email, address);
		            queryRecords.add(queryBook);
		        }
		        System.out.println("Query Records loaded successfully from the file.");
		        showAllQueryRecords();
		    } catch (Exception e) {
		        System.out.println("An error occurred while reading the file: " + e.getMessage());
		        e.printStackTrace();
		    }
		
		
	}
	
	public void showAllQueryRecords() {
//		System.out.printf("Mailo error" + queryRecords);
		for (QueryBook queryElement : queryRecords) {
            System.out.println(queryElement);
        }
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
	
	public int checkForExistingRecord(String name , String birthday) {
		int index = -1;
//		System.out.println("Checking for " + name +birthday);
		for(PhoneBook phoneBook : this.contactRecords ) {
//			System.out.println(phoneBook.getName() + " name :" + name);
//			System.out.println(phoneBook.getBirthday() + " birthday :" + birthday);
			if(phoneBook.getName().equals(name) && phoneBook.getBirthday().equals(birthday)) {
				index = this.contactRecords.indexOf(phoneBook);
				System.out.println("Found");
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
