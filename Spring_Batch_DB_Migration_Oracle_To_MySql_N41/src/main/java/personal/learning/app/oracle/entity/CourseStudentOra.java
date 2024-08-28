package personal.learning.app.oracle.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course_student")
public class CourseStudentOra implements Serializable {
	
	@EmbeddedId
	private CourseStudentIdOra courseStudentId;

	public CourseStudentIdOra getCourseStudentId() {
		return courseStudentId;
	}

	public void setCourseStudentId(CourseStudentIdOra courseStudentId) {
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
		CourseStudentOra other = (CourseStudentOra) obj;
		return Objects.equals(courseStudentId, other.courseStudentId);
	}

	@Override
	public String toString() {
		return "CourseStudentOra [courseStudentId=" + courseStudentId + "]";
	}
	
}
