package personal.learning.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import personal.learning.app.listener.SkipBadRecordListenerForCourse;
import personal.learning.app.listener.SkipBadRecordListenerForCourseStudent;
import personal.learning.app.listener.SkipBadRecordListenerForInstructor;
import personal.learning.app.listener.SkipBadRecordListenerForInstructorDetail;
import personal.learning.app.listener.SkipBadRecordListenerForInstructorJobDetail;
import personal.learning.app.listener.SkipBadRecordListenerForReview;
import personal.learning.app.listener.SkipBadRecordListenerForStudent;
import personal.learning.app.listener.SkipBadRecordListenerForStudentCourse;
import personal.learning.app.mysql.entity.CourseMsq;
import personal.learning.app.mysql.entity.CourseStudentMsq;
import personal.learning.app.mysql.entity.InstructorDetailMsq;
import personal.learning.app.mysql.entity.InstructorJobDetailMsq;
import personal.learning.app.mysql.entity.InstructorMsq;
import personal.learning.app.mysql.entity.ReviewMsq;
import personal.learning.app.mysql.entity.StudentCourseMsq;
import personal.learning.app.mysql.entity.StudentMsq;
import personal.learning.app.oracle.entity.CourseOra;
import personal.learning.app.oracle.entity.CourseStudentOra;
import personal.learning.app.oracle.entity.InstructorDetailOra;
import personal.learning.app.oracle.entity.InstructorJobDetailOra;
import personal.learning.app.oracle.entity.InstructorOra;
import personal.learning.app.oracle.entity.ReviewOra;
import personal.learning.app.oracle.entity.StudentCourseOra;
import personal.learning.app.oracle.entity.StudentOra;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	@Qualifier("practiceDatasource")
	private DataSource practiceDatasource;
	
	@Autowired
	@Qualifier("universityDatasource")
	private DataSource universityDatasource;
	
	@Autowired
	@Qualifier("practiceEntityManagerFactory")
	private EntityManagerFactory practiceEntityManagerFactory;
	
	@Autowired
	@Qualifier("universityEntityManagerFactory")
	private EntityManagerFactory universityEntityManagerFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SkipBadRecordListenerForInstructor skipBadRecordListenerForInstructor;
	
	@Autowired
	private SkipBadRecordListenerForInstructorDetail skipBadRecordListenerForInstructorDetail;
	
	@Autowired
	private SkipBadRecordListenerForCourse skipBadRecordListenerForCourse;
	
	@Autowired
	private SkipBadRecordListenerForReview skipBadRecordListenerForReview;
	
	@Autowired
	public SkipBadRecordListenerForStudent skipBadRecordListenerForStudent;
	
	@Autowired
	public SkipBadRecordListenerForCourseStudent skipBadRecordListenerForCourseStudent;
	
	@Autowired
	public SkipBadRecordListenerForStudentCourse skipBadRecordListenerForStudentCourse;
	
	@Autowired
	public SkipBadRecordListenerForInstructorJobDetail skipBadRecordListenerForInstructorJobDetail;
	
	@Autowired
	private ItemProcessor<InstructorOra, InstructorMsq> jpaItemProcessorForInstructor;
	
	@Autowired
	private ItemProcessor<InstructorDetailOra, InstructorDetailMsq> jpaItemProcessorForInstructorDetail;
	
	@Autowired
	private ItemProcessor<CourseOra, CourseMsq> jpaItemProcessorForCourse;
	
	@Autowired
	private ItemProcessor<ReviewOra, ReviewMsq> jpaItemProcessorForReview;
	
	@Autowired
	private ItemProcessor<StudentOra, StudentMsq> jpaItemProcessorForStudent;
	
	@Autowired
	private ItemProcessor<CourseStudentOra, CourseStudentMsq> jpaItemProcessorForCourseStudent;
	
	@Autowired
	private ItemProcessor<StudentCourseOra, StudentCourseMsq> jpaItemProcessorForStudentCourse;
	
	@Autowired
	private ItemProcessor<InstructorJobDetailOra, InstructorJobDetailMsq> jpaItemProcessorForInstructorJobDetail;
	
	@Autowired
	private JpaTransactionManager jpaTransactionManager;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("MigrationFromOracleToMySql")
								.start(instructorMigrationChunkStep())
								.next(instructorDetailMigrationChunkStep())
								.next(courseMigrationChunkStep())
								.next(reviewMigrationChunkStep())
								.next(studentMigrationChunkStep())
								.next(courseStudentMigrationChunkStep())
								.next(studentCourseMigrationChunkStep())
								.next(instructorJobDetailMigrationChunkStep())
								.build();
	}
	
	public Step instructorMigrationChunkStep() {				
		
		return stepBuilderFactory.get("INSTRUCTOR Migration")
								 .<InstructorOra, InstructorMsq>chunk(3)
								 .reader(jpaCursorItemReaderForInstructor())
								 .processor(jpaItemProcessorForInstructor)
								 .writer(jpaItemWriterForInstructor())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForInstructor)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<InstructorOra> jpaCursorItemReaderForInstructor() {
		
		JpaCursorItemReader<InstructorOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from InstructorOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<InstructorMsq> jpaItemWriterForInstructor() {
		
		JpaItemWriter<InstructorMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////
	
	public Step instructorDetailMigrationChunkStep() {				
		
		return stepBuilderFactory.get("INSTRUCTOR_DETAIL Migration")
								 .<InstructorDetailOra, InstructorDetailMsq>chunk(3)
								 .reader(jpaCursorItemReaderForInstructorDetail())
								 .processor(jpaItemProcessorForInstructorDetail)
								 .writer(jpaItemWriterForInstructorDetail())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForInstructorDetail)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<InstructorDetailOra> jpaCursorItemReaderForInstructorDetail() {
		
		JpaCursorItemReader<InstructorDetailOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from InstructorDetailOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<InstructorDetailMsq> jpaItemWriterForInstructorDetail() {
		
		JpaItemWriter<InstructorDetailMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Step courseMigrationChunkStep() {				
		
		return stepBuilderFactory.get("COURSE Migration")
								 .<CourseOra, CourseMsq>chunk(3)
								 .reader(jpaCursorItemReaderForCourse())
								 .processor(jpaItemProcessorForCourse)
								 .writer(jpaItemWriterForCourse())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForCourse)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<CourseOra> jpaCursorItemReaderForCourse() {
		
		JpaCursorItemReader<CourseOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from CourseOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<CourseMsq> jpaItemWriterForCourse() {
		
		JpaItemWriter<CourseMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Step reviewMigrationChunkStep() {				
		
		return stepBuilderFactory.get("REVIEW Migration")
								 .<ReviewOra, ReviewMsq>chunk(3)
								 .reader(jpaCursorItemReaderForReview())
								 .processor(jpaItemProcessorForReview)
								 .writer(jpaItemWriterForReview())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForReview)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<ReviewOra> jpaCursorItemReaderForReview() {
		
		JpaCursorItemReader<ReviewOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from ReviewOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<ReviewMsq> jpaItemWriterForReview() {
		
		JpaItemWriter<ReviewMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public Step studentMigrationChunkStep() {				
		
		return stepBuilderFactory.get("STUDENT Migration")
								 .<StudentOra, StudentMsq>chunk(3)
								 .reader(jpaCursorItemReaderForStudent())
								 .processor(jpaItemProcessorForStudent)
								 .writer(jpaItemWriterForStudent())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForStudent)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<StudentOra> jpaCursorItemReaderForStudent() {
		
		JpaCursorItemReader<StudentOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from StudentOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<StudentMsq> jpaItemWriterForStudent() {
		
		JpaItemWriter<StudentMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	
	public Step courseStudentMigrationChunkStep() {				
		
		return stepBuilderFactory.get("COURSE_STUDENT Migration")
								 .<CourseStudentOra, CourseStudentMsq>chunk(3)
								 .reader(jpaCursorItemReaderForCourseStudent())
								 .processor(jpaItemProcessorForCourseStudent)
								 .writer(jpaItemWriterForCourseStudent())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForCourseStudent)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<CourseStudentOra> jpaCursorItemReaderForCourseStudent() {
		
		JpaCursorItemReader<CourseStudentOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from CourseStudentOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<CourseStudentMsq> jpaItemWriterForCourseStudent() {
		
		JpaItemWriter<CourseStudentMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	} 
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	public Step studentCourseMigrationChunkStep() {				
		
		return stepBuilderFactory.get("STUDENT_COURSE Migration")
								 .<StudentCourseOra, StudentCourseMsq>chunk(3)
								 .reader(jpaCursorItemReaderForStudentCourse())
								 .processor(jpaItemProcessorForStudentCourse)
								 .writer(jpaItemWriterForStudentCourse())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForStudentCourse)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<StudentCourseOra> jpaCursorItemReaderForStudentCourse() {
		
		JpaCursorItemReader<StudentCourseOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from StudentCourseOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<StudentCourseMsq> jpaItemWriterForStudentCourse() {
		
		JpaItemWriter<StudentCourseMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Step instructorJobDetailMigrationChunkStep() {				
		
		return stepBuilderFactory.get("INSTRUCTOR_JOB_DETAIL Migration")
								 .<InstructorJobDetailOra, InstructorJobDetailMsq>chunk(3)
								 .reader(jpaCursorItemReaderForinstructorJobDetail())
								 .processor(jpaItemProcessorForInstructorJobDetail)
								 .writer(jpaItemWriterForinstructorJobDetail())
								 .faultTolerant()
								 .skip(Exception.class)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListenerForInstructorJobDetail)
								 .transactionManager(jpaTransactionManager)
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
	}
	
	@StepScope
	@Bean
	public JpaCursorItemReader<InstructorJobDetailOra> jpaCursorItemReaderForinstructorJobDetail() {
		
		JpaCursorItemReader<InstructorJobDetailOra> jpaCursorItemReader = new JpaCursorItemReader<>();
		jpaCursorItemReader.setEntityManagerFactory(practiceEntityManagerFactory);
		jpaCursorItemReader.setQueryString("from InstructorJobDetailOra");
		
		return jpaCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JpaItemWriter<InstructorJobDetailMsq> jpaItemWriterForinstructorJobDetail() {
		
		JpaItemWriter<InstructorJobDetailMsq> jpaItemWriter = new JpaItemWriter<>();
		
		jpaItemWriter.setEntityManagerFactory(universityEntityManagerFactory);
		
		return jpaItemWriter;
	} 
	
}
