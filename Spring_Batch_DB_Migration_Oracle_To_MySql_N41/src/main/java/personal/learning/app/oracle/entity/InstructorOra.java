package personal.learning.app.oracle.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class InstructorOra {
	
	@Id
	private BigInteger id;
	
	@Column(name = "instructor_first_name")
	private String instructorFirstName;
	
	@Column(name = "instructor_last_name")
	private String instructorLastName;
	
	@Column(name = "instructor_email")
	private String instructorEmail;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getInstructorFirstName() {
		return instructorFirstName;
	}

	public void setInstructorFirstName(String instructorFirstName) {
		this.instructorFirstName = instructorFirstName;
	}

	public String getInstructorLastName() {
		return instructorLastName;
	}

	public void setInstructorLastName(String instructorLastName) {
		this.instructorLastName = instructorLastName;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

	@Override
	public String toString() {
		return "InstructorOra [id=" + id + ", instructorFirstName=" + instructorFirstName + ", instructorLastName="
				+ instructorLastName + ", instructorEmail=" + instructorEmail + "]";
	}
	
}
