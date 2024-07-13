package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job myJob() {
		return jobBuilderFactory.get("My job")
								.start(step1())
								.next(step2())
								.build();	
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step 1")
								 .tasklet(task1())
								 .build();
	}
	
	
	public Step step2() {
		return stepBuilderFactory.get("Step 2")
								 .tasklet(task2())
								 .build();
	}
	
	private Tasklet task1() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				System.out.println("Executing task 1");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	private Tasklet task2() {
		return new Tasklet() {
			
			@Override
			public  RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				System.out.println("Executing task 2");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
}
