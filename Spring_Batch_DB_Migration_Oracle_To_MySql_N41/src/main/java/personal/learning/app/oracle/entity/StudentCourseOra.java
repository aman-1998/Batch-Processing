package personal.learning.app.oracle.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student_course")
public class StudentCourseOra {
	
	@Id
	private BigInteger id;
	
	@Column(name = "student_id")
	private BigInteger studentId;
	
	@Column(name = "course_id")
	private BigInteger courseId;
	
	@Column(name = "date_of_purchase")
	@Temporal(TemporalType.DATE)
	private Date dateOfPurchase;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getStudentId() {
		return studentId;
	}

	public void setStudentId(BigInteger studentId) {
		this.studentId = studentId;
	}

	public BigInteger getCourseId() {
		return courseId;
	}

	public void setCourseId(BigInteger courseId) {
		this.courseId = courseId;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	@Override
	public String toString() {
		return "StudentCourseOra [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId
				+ ", dateOfPurchase=" + dateOfPurchase + "]";
	}
	
}
