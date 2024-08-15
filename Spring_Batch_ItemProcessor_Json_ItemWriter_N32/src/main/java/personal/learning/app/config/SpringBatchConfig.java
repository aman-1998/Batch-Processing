package personal.learning.app.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import personal.learning.app.model.CustomerJdbc;
import personal.learning.app.model.CustomerJson;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private Tasklet testTask;
	
	@Autowired
	private ItemProcessor<CustomerJdbc, CustomerJson> jsonItemProcessor;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("TestJob")
								.start(simpleChunkStep())
								.next(taskletStep())
								.build();
	}
	
	public Step simpleChunkStep() {
		
		return stepBuilderFactory.get("Simple Step")
								 .<CustomerJdbc, CustomerJson>chunk(3)
								 .reader(jdbcCursorItemReader())
								 .processor(jsonItemProcessor)
								 .writer(jsonFileItemWriter(null))
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@StepScope
	@Bean
	public JdbcCursorItemReader<CustomerJdbc> jdbcCursorItemReader() {
		JdbcCursorItemReader<CustomerJdbc> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		
		jdbcCursorItemReader.setDataSource(datasource);
		jdbcCursorItemReader.setSql("select customer_pk as pk, email as email, name as name, gender as gender, "
				+ "language as lang, dob as dob, address as address from customer");
		
		BeanPropertyRowMapper<CustomerJdbc> beanPropertyRowMapper = new BeanPropertyRowMapper<>();
		beanPropertyRowMapper.setMappedClass(CustomerJdbc.class);
		
		jdbcCursorItemReader.setRowMapper(beanPropertyRowMapper);
		
		jdbcCursorItemReader.setCurrentItemCount(2);
		jdbcCursorItemReader.setMaxItemCount(8);
		
		return jdbcCursorItemReader;
	}
	
	@StepScope
	@Bean
	public JsonFileItemWriter<CustomerJson> jsonFileItemWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {
		
		JsonFileItemWriter<CustomerJson> jsonFileItemWriter = new JsonFileItemWriter<>(fileSystemResource, new JacksonJsonObjectMarshaller<CustomerJson>());
		
		return jsonFileItemWriter;
	}
}
