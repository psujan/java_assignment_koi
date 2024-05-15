package ecbsystem;

public class PhoneBook {
	private String name , birthday , email , address , phone;
	
	public PhoneBook() {
		this.name = "";
		this.birthday = "";
		this.phone = "";
		this.email = "";
		this.address = "";
	}
	
	public String toString() {
        return "Name: " + name + 
                "\nBirthday: " + birthday +
                "\nEmail: " + email +
                "\nPhone: " + phone+
                "\nAddress:" + address;
    }
	
	public PhoneBook(String name , String birthday , String phone , String email , String address ) {
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
