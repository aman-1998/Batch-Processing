package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
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

import personal.learning.app.model.Student;
import personal.learning.app.service.StudentService;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private ItemWriter<Student> consoleItemWriterForStudent;
	
	@Autowired
	private Tasklet testTask;
	
	@Autowired
	private StudentService studentService;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("TestJob")
								.start(simpleChunkStep())
								.next(taskletStep())
								.build();
	}
	
	public Step simpleChunkStep() {
		
		return stepBuilderFactory.get("Simple Step")
								 .<Student, Student>chunk(3)
								 .reader(flatFileItemReader(null))
								 //.processor(itemProcessorForList)
								 .writer(itemWriterAdapter())
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemReader<Student> flatFileItemReader(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		
		FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
		
		//flatFileItemReader.setResource(new FileSystemResource(new File("C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\Spring_Batch_Flat_File_ItemReader_N22\\inputFile\\StudentData.csv")));
		flatFileItemReader.setResource(fileSystemResource);
		flatFileItemReader.setName("csvReader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
	}

	private LineMapper<Student> lineMapper() {
		
		DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(true);
		delimitedLineTokenizer.setNames("Roll", "First Name", "Last Name", "Email");
		
		BeanWrapperFieldSetMapper<Student> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Student.class);
		
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return lineMapper;
	}
	
	@StepScope
	@Bean
	public ItemWriterAdapter<Student> itemWriterAdapter() {
		
		ItemWriterAdapter<Student> itemWriterAdapter = new ItemWriterAdapter<>();
		
		itemWriterAdapter.setTargetObject(studentService);
		itemWriterAdapter.setTargetMethod("restCallToCreateStudent");
		
		return itemWriterAdapter;
	}
}
