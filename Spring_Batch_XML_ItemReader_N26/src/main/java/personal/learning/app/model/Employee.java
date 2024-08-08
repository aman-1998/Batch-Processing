package personal.learning.app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.NONE)
public class Employee {
	
	@XmlElement(name = "empId")
	private Long id;
	
	@XmlElement(name = "firstName")
	private String firstName;
	
	@XmlElement(name = "lastName")
	private String lastName;
	
	@XmlElement(name = "domainId")
	private String domain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName 
				+ ", lastName=" + lastName + ", domain=" + domain + "]";
	}
}
