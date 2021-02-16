
public class Contact  {
	private String fName;
	private String lName;
	private String address;
	private int phoneNumber;
	
	public Contact(String fName, String lName, String address, int phoneNumber) {
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getFullName() {
		return this.fName + " " + this.lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "First Name: " + fName + ", Last Name: " + lName + ", Address: " 
				+ address + ", Phone Number: " + phoneNumber +"\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Contact) {
			Contact otherContact = (Contact)obj;
			return fName.equalsIgnoreCase(otherContact.fName) && lName.equalsIgnoreCase(otherContact.lName) && address.equalsIgnoreCase(otherContact.address); 
		}else return false;
		
	}
	
    public int compareTo(Contact a){
        return (getFullName().compareTo(a.getFullName()));
    }
}
