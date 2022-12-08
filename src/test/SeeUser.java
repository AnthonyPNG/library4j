package test;

public class SeeUser {	
	int iduser, phone;
	String email, firstName, lastName;
	
	public SeeUser(int iduser, int phone, String email, String firstName, String lastName) {
		this.iduser = iduser;
		this.phone = phone;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
