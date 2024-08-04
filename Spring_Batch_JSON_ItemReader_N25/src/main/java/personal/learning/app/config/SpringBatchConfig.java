package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import personal.learning.app.model.Customer;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private ItemWriter<Customer> consoleItemWriterForCustomer;
	
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
								 .<Customer, Customer>chunk(3)
								 .reader(jsonItemReader(null))
								 //.processor(itemProcessorForList)
								 .writer(consoleItemWriterForCustomer)
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@StepScope
	@Bean
	public JsonItemReader<Customer> jsonItemReader(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		
		JsonItemReader<Customer> jsonItemReader = new JsonItemReader<>();
		
		jsonItemReader.setResource(fileSystemResource);
		
		jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(Customer.class));
		
		jsonItemReader.setName("jsonReader");
		jsonItemReader.setMaxItemCount(8);
		jsonItemReader.setCurrentItemCount(2);
		
		return jsonItemReader;
	}
}
