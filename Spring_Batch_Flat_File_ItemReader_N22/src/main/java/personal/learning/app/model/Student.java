package personal.learning.app.model;

public class Student {
	
	private Integer roll;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

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
		return "Student [roll=" + roll + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
