package ecbsystem;

import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;

public class EcbController {
	private ArrayList<PhoneBook> contactRecords = new ArrayList<PhoneBook>();
	
	public void addRecord(PhoneBook pb) {
		this.contactRecords.add(pb);
		this.addRecordsToFile();
	}
	
	public void deleteRecord() {
		
	}
	
	public void queryRecord() {
		
	}
	
	public void saveRecord() {
		
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
}
