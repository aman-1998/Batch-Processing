package personal.learning.app.mysql.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseMsq {
	
	@Id
	private BigInteger id;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "instructor_id")
	private BigInteger instructorId;
	
	@Column(name = "price")
	private BigInteger price;
	
	@Column(name = "course_description")
	private String courseDescription;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigInteger getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(BigInteger instructorId) {
		this.instructorId = instructorId;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	@Override
	public String toString() {
		return "CourseMsq [id=" + id + ", courseName=" + courseName + ", instructorId=" + instructorId + ", price="
				+ price + ", courseDescription=" + courseDescription + "]";
	}

}
