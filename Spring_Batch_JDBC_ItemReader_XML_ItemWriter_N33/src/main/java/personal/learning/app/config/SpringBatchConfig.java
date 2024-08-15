package personal.learning.app.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import personal.learning.app.model.Customer;

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
								 .reader(jdbcCursorItemReader())
								 //.processor(itemProcessorForList)
								 .writer(staxEventItemWriter(null))
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@StepScope
	@Bean
	public JdbcCursorItemReader<Customer> jdbcCursorItemReader() {
		JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		
		jdbcCursorItemReader.setDataSource(datasource);
		jdbcCursorItemReader.setSql("select customer_pk as pk, email as email, name as name, gender as gender, "
				+ "language as lang, dob as dob, address as address from customer");
		
		BeanPropertyRowMapper<Customer> beanPropertyRowMapper = new BeanPropertyRowMapper<>();
		beanPropertyRowMapper.setMappedClass(Customer.class);
		
		jdbcCursorItemReader.setRowMapper(beanPropertyRowMapper);
		
		jdbcCursorItemReader.setCurrentItemCount(2);
		jdbcCursorItemReader.setMaxItemCount(8);
		
		return jdbcCursorItemReader;
	}
	
	@StepScope
	@Bean
	public StaxEventItemWriter<Customer> staxEventItemWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {
		
		StaxEventItemWriter<Customer> staxEventItemWriter = new StaxEventItemWriter<>();
		
		staxEventItemWriter.setResource(fileSystemResource);
		staxEventItemWriter.setRootTagName("customers");
		
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Customer.class);
		
		staxEventItemWriter.setMarshaller(jaxb2Marshaller);
		
		return staxEventItemWriter;
	}
}
