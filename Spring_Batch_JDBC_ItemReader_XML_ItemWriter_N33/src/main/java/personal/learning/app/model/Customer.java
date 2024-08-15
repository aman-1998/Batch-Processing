package personal.learning.app.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {
	
	@XmlElement(name = "id")
	private BigInteger pk;
	
	@XmlElement(name = "fullName")
	private String name;
	
	@XmlElement(name = "emailId")
	private String email;
	
	@XmlElement(name = "gender")
	private String gender;
	
	@XmlElement(name = "language")
	private String lang;
	
	@XmlElement(name = "dob")
	private String dob;
	
	@XmlElement(name = "address")
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
