package personal.learning.app.model;

import java.math.BigInteger;

public class Customer {
	
	private BigInteger pk;
	
	private String name;
	
	private String email;
	
	private String gender;
	
	private String lang;
	
	private String dob;
	
	private String address;

	public BigInteger getPk() {
		return pk;
	}

	public void setPk(BigInteger pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [pk=" + pk + ", name=" + name + ", email=" + email + ", gender=" + gender + ", lang=" + lang
				+ ", dob=" + dob + ", address=" + address + "]";
	}
	
}
