package ecbsystem;

public class QueryBook extends PhoneBook {
	private String query;
	
	public QueryBook() {
		super();
		this.query = "";
	}
	
	public String toString() {
        return "Query: " + this.query +
        		"\nName: " + this.getName() + 
                "\nBirthday: " + this.getBirthday() +
                "\nEmail: " + this.getEmail() +
                "\nPhone: " + this.getPhone()+
                "\nAddress: " + this.getAddress();
    }
	
	public QueryBook(String query, String name , String birthday , String phone , String email , String address ) {
		super(name , birthday, phone,email, address);
		this.query = query;
	}
	
}