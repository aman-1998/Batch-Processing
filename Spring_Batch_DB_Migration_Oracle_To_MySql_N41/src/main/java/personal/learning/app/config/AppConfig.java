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
import personal.learning.app.listener.SkipBadRecordListenerForCourse;
import personal.learning.app.listener.SkipBadRecordListenerForCourseStudent;
import personal.learning.app.listener.SkipBadRecordListenerForInstructor;
import personal.learning.app.listener.SkipBadRecordListenerForInstructorDetail;
import personal.learning.app.listener.SkipBadRecordListenerForInstructorJobDetail;
import personal.learning.app.listener.SkipBadRecordListenerForReview;
import personal.learning.app.listener.SkipBadRecordListenerForStudent;
import personal.learning.app.listener.SkipBadRecordListenerForStudentCourse;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public JpaItemProcessorForInstructor JpaItemProcessor() {
		return new JpaItemProcessorForInstructor();
	}
	
	@Bean
	public SkipBadRecordListenerForInstructor skipBadRecordListenerForInstructor() {
		return new SkipBadRecordListenerForInstructor();
	}
	
	@Bean
	public SkipBadRecordListenerForInstructorDetail SkipBadRecordListenerForInstructorDetail() {
		return new SkipBadRecordListenerForInstructorDetail();
	}
	
	@Bean
	public SkipBadRecordListenerForCourse skipBadRecordListenerForCourse() {
		return new SkipBadRecordListenerForCourse();
	}
	
	@Bean
	public SkipBadRecordListenerForReview SkipBadRecordListenerForReview() {
		return new SkipBadRecordListenerForReview();
	}
	
	@Bean
	public SkipBadRecordListenerForStudent SkipBadRecordListenerForStudent() {
		return new SkipBadRecordListenerForStudent();
	}
	
	@Bean
	public SkipBadRecordListenerForCourseStudent skipBadRecordListenerForCourseStudent() {
		return new SkipBadRecordListenerForCourseStudent();
	}
	
	@Bean
	public SkipBadRecordListenerForStudentCourse skipBadRecordListenerForStudentCourse() {
		return new SkipBadRecordListenerForStudentCourse();
	}
	
	@Bean
	public SkipBadRecordListenerForInstructorJobDetail skipBadRecordListenerForInstructorJobDetail() {
		return new SkipBadRecordListenerForInstructorJobDetail();
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
