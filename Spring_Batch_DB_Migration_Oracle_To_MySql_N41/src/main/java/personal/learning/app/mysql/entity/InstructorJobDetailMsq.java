package personal.learning.app.mysql.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_job_detail")
public class InstructorJobDetailMsq {
	
	@Id
	private BigInteger id;
	
	@Column(name = "organization")
	private String organization;
	
	@Column(name = "office_address")
	private String officeAddress;
	
	@Column(name = "office_hr_phone_no")
	private BigInteger officeHrPhoneNo;
	
	@Column(name = "office_hr_email")
	private String officeHrEmail;
	
	@Column(name = "instructor_id")
	private BigInteger instructorId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public BigInteger getOfficeHrPhoneNo() {
		return officeHrPhoneNo;
	}

	public void setOfficeHrPhoneNo(BigInteger officeHrPhoneNo) {
		this.officeHrPhoneNo = officeHrPhoneNo;
	}

	public String getOfficeHrEmail() {
		return officeHrEmail;
	}

	public void setOfficeHrEmail(String officeHrEmail) {
		this.officeHrEmail = officeHrEmail;
	}

	public BigInteger getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(BigInteger instructorId) {
		this.instructorId = instructorId;
	}
	
}
