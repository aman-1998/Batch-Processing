package personal.learning.app.model;

import java.math.BigInteger;

public class CustomerJson {
	
	private BigInteger id;
	
	private String fullName;
	
	private String emailId;
	
	private String gender;
	
	private String lang;
	
	private String dob;
	
	private String address;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
		return "CustomerJson [id=" + id + ", fullName=" + fullName + ", emailId=" + emailId + ", gender=" + gender
				+ ", lang=" + lang + ", dob=" + dob + ", address=" + address + "]";
	}
	
}
