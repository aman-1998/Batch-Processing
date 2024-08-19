package personal.learning.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentJson {
	
	@JsonProperty("roll")
	private Integer roll;
	
	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("shoolName")
	private String school;

	public Integer getRoll() {
		return roll;
	}

	public void setRoll(Integer roll) {
		this.roll = roll;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "StudentJson [roll=" + roll + ", fullname=" + fullName + ", emailId=" 
				+ emailId + ", school=" + school + "]";
	}
	
}
