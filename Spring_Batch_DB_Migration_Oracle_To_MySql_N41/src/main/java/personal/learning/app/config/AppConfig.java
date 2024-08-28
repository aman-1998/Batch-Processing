package personal.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.processor.JpaItemProcessorForCourse;
import personal.learning.app.item.processor.JpaItemProcessorForCourseStudent;
import personal.learning.app.item.processor.JpaItemProcessorForInstructor;
import personal.learning.app.item.processor.JpaItemProcessorForInstructorDetail;
import personal.learning.app.item.processor.JpaItemProcessorForInstructorJobDetail;
import personal.learning.app.item.processor.JpaItemProcessorForReview;
import personal.learning.app.item.processor.JpaItemProcessorForStudent;
import personal.learning.app.item.processor.JpaItemProcessorForStudentCourse;
import personal.learning.app.listener.SkipBadRecordListener;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public JpaItemProcessorForInstructor JpaItemProcessor() {
		return new JpaItemProcessorForInstructor();
	}
	
	@Bean
	public SkipBadRecordListener skipBadRecordListener() {
		return new SkipBadRecordListener();
	}
	
	@Bean
	public JpaItemProcessorForInstructor jpaItemProcessorForInstructor() {
		return new JpaItemProcessorForInstructor();
	}
	
	@Bean
	public JpaItemProcessorForInstructorDetail jpaItemProcessorForInstructorDetail() {
		return new JpaItemProcessorForInstructorDetail();
	}
	
	@Bean
	public JpaItemProcessorForCourse jpaItemProcessorForCourse() {
		return new JpaItemProcessorForCourse();
	}
	
	@Bean
	public JpaItemProcessorForReview jpaItemProcessorForReview() {
		return new JpaItemProcessorForReview();
	}
	
	@Bean
	public JpaItemProcessorForStudent jpaItemProcessorForStudent() {
		return new JpaItemProcessorForStudent();
	}
	
	@Bean
	public JpaItemProcessorForCourseStudent jpaItemProcessorForCourseStudent() {
		return new JpaItemProcessorForCourseStudent();
	}
	
	@Bean
	public JpaItemProcessorForStudentCourse jpaItemProcessorForStudentCourse() {
		return new JpaItemProcessorForStudentCourse();
	}
	
	@Bean
	public JpaItemProcessorForInstructorJobDetail jpaItemProcessorForInstructorJobDetail() {
		return new JpaItemProcessorForInstructorJobDetail();
	}
}
