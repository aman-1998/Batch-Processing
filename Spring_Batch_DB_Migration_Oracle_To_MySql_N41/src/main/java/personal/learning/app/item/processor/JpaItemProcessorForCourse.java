package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.CourseMsq;
import personal.learning.app.oracle.entity.CourseOra;

public class JpaItemProcessorForCourse implements ItemProcessor<CourseOra, CourseMsq> {

	@Override
	public CourseMsq process(CourseOra item) throws Exception {
		
		CourseMsq courseMsq = new CourseMsq();
		
		courseMsq.setId(item.getId());
		courseMsq.setCourseName(item.getCourseName());
		courseMsq.setInstructorId(item.getInstructorId());
		courseMsq.setPrice(item.getPrice());
		courseMsq.setCourseDescription(item.getCourseDescription());
		
		return courseMsq;
	}

}
