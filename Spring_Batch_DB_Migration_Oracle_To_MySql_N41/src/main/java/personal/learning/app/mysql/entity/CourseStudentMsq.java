package personal.learning.app.mysql.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course_student")
public class CourseStudentMsq implements Serializable {
	
	@EmbeddedId
	private CourseStudentIdMsq courseStudentId;

	public CourseStudentIdMsq getCourseStudentId() {
		return courseStudentId;
	}

	public void setCourseStudentId(CourseStudentIdMsq courseStudentId) {
		this.courseStudentId = courseStudentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseStudentMsq other = (CourseStudentMsq) obj;
		return Objects.equals(courseStudentId, other.courseStudentId);
	}

	@Override
	public String toString() {
		return "CourseStudentMsq [courseStudentId=" + courseStudentId + "]";
	}
	
}
