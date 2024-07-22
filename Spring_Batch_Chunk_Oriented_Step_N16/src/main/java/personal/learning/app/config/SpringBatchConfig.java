package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
	private ItemWriter<String> ItemWriterForList;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("Test Job")
								.start(simpleStep())
								.build();
	}
	
	public Step simpleStep() {
		return stepBuilderFactory.get("Simple Step")
								 .<Integer, String>chunk(3)
								 .reader(itemReaderForList)
								 .processor(itemProcessorForList)
								 .writer(ItemWriterForList)
								 .build();
	}
}
