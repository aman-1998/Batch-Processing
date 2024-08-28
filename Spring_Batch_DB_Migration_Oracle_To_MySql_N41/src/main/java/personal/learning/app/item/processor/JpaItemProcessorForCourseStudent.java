package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.CourseStudentIdMsq;
import personal.learning.app.mysql.entity.CourseStudentMsq;
import personal.learning.app.oracle.entity.CourseStudentOra;

public class JpaItemProcessorForCourseStudent implements ItemProcessor<CourseStudentOra, CourseStudentMsq> {

	@Override
	public CourseStudentMsq process(CourseStudentOra item) throws Exception {
		
		CourseStudentMsq courseStudentMsq = new CourseStudentMsq();
		
		CourseStudentIdMsq courseStudentIdMsq = new CourseStudentIdMsq();
		courseStudentIdMsq.setCourseId(item.getCourseStudentId().getCourseId());
		courseStudentIdMsq.setStudentId(item.getCourseStudentId().getStudentId());
		
		courseStudentMsq.setCourseStudentId(courseStudentIdMsq);
		
		return courseStudentMsq;
	}

}
