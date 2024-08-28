package personal.learning.app.mysql.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseStudentIdMsq implements Serializable {
	
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "course_id")
	private String courseId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseStudentIdMsq other = (CourseStudentIdMsq) obj;
		return Objects.equals(courseId, other.courseId) && Objects.equals(studentId, other.studentId);
	}

	@Override
	public String toString() {
		return "CourseStudent [studentId=" + studentId + ", courseId=" + courseId + "]";
	}
}
