package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.StudentCourseMsq;
import personal.learning.app.oracle.entity.StudentCourseOra;

public class JpaItemProcessorForStudentCourse implements ItemProcessor<StudentCourseOra, StudentCourseMsq> {

	@Override
	public StudentCourseMsq process(StudentCourseOra item) throws Exception {
		
		StudentCourseMsq studentCourseMsq = new StudentCourseMsq();
		
		studentCourseMsq.setId(item.getId());
		studentCourseMsq.setStudentId(item.getStudentId());
		studentCourseMsq.setCourseId(item.getCourseId());
		studentCourseMsq.setDateOfPurchase(item.getDateOfPurchase());
		
		return studentCourseMsq;
	}

}
