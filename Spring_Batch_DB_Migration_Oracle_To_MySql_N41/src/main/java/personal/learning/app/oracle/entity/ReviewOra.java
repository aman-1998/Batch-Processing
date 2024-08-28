package personal.learning.app.oracle.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class ReviewOra {
	
	@Id
	private BigInteger id;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "course_id")
	private BigInteger courseId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigInteger getCourseId() {
		return courseId;
	}

	public void setCourseId(BigInteger courseId) {
		this.courseId = courseId;
	}
}
