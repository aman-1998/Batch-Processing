package personal.learning.app.config;

import java.math.BigInteger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import personal.learning.app.entity.CustomerCsv;
import personal.learning.app.entity.CustomerDb;
import personal.learning.app.listener.SkipBadRecordListenerForCustomer;
import personal.learning.app.repository.CustomerRepository;

/*

The difference between JpaItemWriter and RepositoryItemWriter regarding the need for a dialect in application.properties comes down to how the Spring configuration is set up and how these components interact with the underlying JPA infrastructure.

1. JpaItemWriter:
JPA Configuration: When you use JpaItemWriter, you're typically working within a context where JPA is already configured, 
and the necessary EntityManagerFactory has been set up. This configuration includes the JPA vendor-specific properties, 
such as the dialect, which are usually defined in the JPA configuration (e.g., via spring.jpa.properties.hibernate.dialect).

EntityManager Handling: JpaItemWriter delegates the actual database interactions to the EntityManager provided by the 
EntityManagerFactory. The EntityManagerFactory is configured with the dialect as part of the JPA setup, so the 
JpaItemWriter doesn't need to worry about the dialect directly. It simply uses the EntityManager to persist entities.


2. RepositoryItemWriter:
Spring Data JPA Repositories: RepositoryItemWriter works directly with Spring Data JPA repositories. These repositories 
rely on the same JPA infrastructure as JpaItemWriter, but they are more tightly integrated with the Spring Data JPA 
framework.

Dialect Requirement: Since RepositoryItemWriter works at a higher abstraction level and uses repository methods, 
Spring Data JPA needs to be aware of the dialect to correctly generate the SQL queries needed for these operations. 
The dialect information is critical for repositories to function properly, hence it is explicitly required 
in application.properties.


Key Differences:
JpaItemWriter: Inherits its configuration from the JPA context, where the dialect is typically set as part of the 
overall JPA setup. Since it uses an EntityManager directly, it doesn’t require a separate dialect configuration.

RepositoryItemWriter: Relies on Spring Data JPA repositories, which need explicit dialect configuration 
in application.properties because they are responsible for generating SQL queries through the ORM framework.

In Summary:
JpaItemWriter assumes that JPA is already fully configured, including the dialect.
RepositoryItemWriter requires explicit dialect configuration because it works with repositories that generate SQL 
based on the database type.
This distinction is rooted in the configuration and abstraction levels at which each writer operates within the 
Spring ecosystem.

 */

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SkipBadRecordListenerForCustomer skipBadRecordListenerForCustomer;
	
	@Autowired
	private ItemProcessor<CustomerCsv, CustomerDb> repositoryItemProcessorForCustomer;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("DumpHugeData")
								.start(dumpCustomerDataChunkStep())
								.build();
	}
	
	@Bean
	public Step dumpCustomerDataChunkStep() {				
		
		return stepBuilderFactory.get("Dumping huge data into from csv to database")
								 .<CustomerCsv, CustomerDb>chunk(10)
								 .reader(flatFileItemReaderCustomer(null))
								 .processor(repositoryItemProcessorForCustomer)
								 .writer(repositoryItemWriterForCustomer())
								 //.faultTolerant()
								 //.skip(Exception.class)
								 //.skipPolicy(new AlwaysSkipItemSkipPolicy())
								 //.listener(skipBadRecordListenerForCustomer)
								 .taskExecutor(taskExecutor())
								 .build();
		
		/*
		 * Note: Retry mechanism is applicable only for ItemProcessor and ItemWriter
		 */
		
		/*
		 * faultTolerant doesn't not work in Multi-Thread environment.
		 * So, in order to use faultTolerant, we need partitioner
		 */
		
	}
	
	
	@StepScope
	@Bean
	public FlatFileItemReader<CustomerCsv> flatFileItemReaderCustomer(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		
		FlatFileItemReader<CustomerCsv> flatFileItemReader = new FlatFileItemReader<>();
		
		//flatFileItemReader.setResource(new FileSystemResource(new File("C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\Spring_Batch_Flat_File_ItemReader_N22\\inputFile\\StudentData.csv")));
		flatFileItemReader.setResource(fileSystemResource);
		flatFileItemReader.setName("csvReader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
	}

	private LineMapper<CustomerCsv> lineMapper() {
		
		DefaultLineMapper<CustomerCsv> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(true);
		delimitedLineTokenizer.setNames("Id", "First Name", "Last Name", "Email", "Gender", "Contact No", "Country", "Dob");
		
		BeanWrapperFieldSetMapper<CustomerCsv> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(CustomerCsv.class);
		
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return lineMapper;
	}
	
	@Bean
	public RepositoryItemWriter<CustomerDb> repositoryItemWriterForCustomer() {
		
		RepositoryItemWriter<CustomerDb> repositoryItemWriter = new RepositoryItemWriter<>();
		repositoryItemWriter.setRepository(customerRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(10); // Adjust based on your needs
	    taskExecutor.setMaxPoolSize(20);
	    taskExecutor.setQueueCapacity(25);
	    taskExecutor.setThreadNamePrefix("spring-batch-thread-");
	    taskExecutor.initialize();
	    return taskExecutor;
	}
	
}
