package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
	
	@Autowired
	private ItemReader<Integer> itemReaderForList;
	
	@Autowired
	private ItemProcessor<Integer, String> itemProcessorForList;
	
	@Autowired
	private ItemWriter<String> itemWriterForList;
	
	@Autowired
	private Tasklet testTask;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("TestJob")
								.start(simpleChunkStep())
								.next(taskletStep())
								.build();
	}
	
	public Step simpleChunkStep() {
		
		return stepBuilderFactory.get("Simple Step")
								 .<Integer, String>chunk(3)
								 .reader(itemReaderForList)
								 .processor(itemProcessorForList)
								 .writer(itemWriterForList)
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@Bean
	public Job myPracticeJob() {
		
		return jobBuilderFactory.get("PracticeJob")
								.start(taskletStep1())
								.build();
	}
	
	public Step taskletStep1() {
		
		return stepBuilderFactory.get("Practice Step")
								 .tasklet(tasklet1())
								 .tasklet(tasklet2())
								 .build();
				
	}
	
	public Tasklet tasklet1() {
		
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				System.out.println("Executing practice task1");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	public Tasklet tasklet2() {
		
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				System.out.println("Executing practice task2");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	@Bean
	public Job myTestingJob() {
		
		return jobBuilderFactory.get("TestingJob")
								.start(testingStep())
								.build();
	}
	
	public Step testingStep() {
		
		return stepBuilderFactory.get("Testing step")
								 .tasklet(
									new Tasklet() {
										
										@Override
										public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
											System.out.println("Executing testing task");
											return RepeatStatus.FINISHED;
										}
									})
								 .build();
	}
}
