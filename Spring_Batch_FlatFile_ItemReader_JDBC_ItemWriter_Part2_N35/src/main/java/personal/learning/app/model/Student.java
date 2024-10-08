package personal.learning.app.model;

import java.math.BigInteger;

public class Student {
	
	private BigInteger id;
	
	private Integer roll;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getRoll() {
		return roll;
	}

	public void setRoll(Integer roll) {
		this.roll = roll;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", roll=" + roll + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}
}
