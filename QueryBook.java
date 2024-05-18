package ecbsystem;

public class QueryBook {
	private String query, name , birthday , email , address , phone;
	
	public QueryBook() {
		this.query ="";
		this.name = "";
		this.birthday = "";
		this.phone = "";
		this.email = "";
		this.address = "";
	}
	
	public String toString() {
        return "Query: " + query +
        		"\nName: " + name + 
                "\nBirthday: " + birthday +
                "\nEmail: " + email +
                "\nPhone: " + phone+
                "\nAddress: " + address;
    }
	
	public QueryBook(String query, String name , String birthday , String phone , String email , String address ) {
		this.query = query;
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
}