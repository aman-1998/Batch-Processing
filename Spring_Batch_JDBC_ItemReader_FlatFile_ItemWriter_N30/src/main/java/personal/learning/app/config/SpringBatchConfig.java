package personal.learning.app.config;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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
								 .writer(flatFileItemWriter(null))
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
	public FlatFileItemWriter<Customer> flatFileItemWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {
		FlatFileItemWriter<Customer> flatFileItemWriter = new FlatFileItemWriter<>();
		
		flatFileItemWriter.setResource(fileSystemResource);
		
		flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Id,Email,Name,Gender,Language,Date Of Birth,Address");
			}
		});
		
		BeanWrapperFieldExtractor<Customer> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
		beanWrapperFieldExtractor.setNames(new String[] {"pk", "email", "name", "gender", "lang", "dob", "address"});
		
		DelimitedLineAggregator<Customer> delimitedLineAggregator = new DelimitedLineAggregator<>();
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		
		flatFileItemWriter.setLineAggregator(delimitedLineAggregator);
		
		flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
			
			@Override
			public void writeFooter(Writer writer) throws IOException {
				writer.write("Created at : " + LocalDateTime.now());
			}
		});
		
		return flatFileItemWriter;
	}
}
