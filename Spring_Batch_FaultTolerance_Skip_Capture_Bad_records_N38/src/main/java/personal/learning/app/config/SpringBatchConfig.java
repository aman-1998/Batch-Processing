package personal.learning.app.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import personal.learning.app.listener.SkipBadRecordListener;
import personal.learning.app.model.StudentCsv;
import personal.learning.app.model.StudentJson;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private Tasklet testTask;
	
	@Autowired
	private ItemProcessor<StudentCsv, StudentJson> flatFileItemProcessor;
	
	@Autowired
	private SkipBadRecordListener skipBadRecordListener;
	
	@Bean
	public Job myJob() {
		
		return jobBuilderFactory.get("TestJob")
								.start(simpleChunkStep())
								.next(taskletStep())
								.build();
	}
	
	public Step simpleChunkStep() {
		
//		return stepBuilderFactory.get("Simple Step")
//								 .<Student, Student>chunk(3)
//								 .reader(flatFileItemReader(null))
//								 //.processor(itemProcessorForList)
//								 .writer(jsonFileItemWriter(null))
//								 .faultTolerant()
//								 .skip(FlatFileParseException.class)
//								 .skip(NullPointerException.class)
//								 .skipLimit(3)
//								 .build();
	
		
//		return stepBuilderFactory.get("Simple Step")
//								 .<Student, Student>chunk(3)
//								 .reader(flatFileItemReader(null))
//								 //.processor(itemProcessorForList)
//								 .writer(jsonFileItemWriter(null))
//								 .faultTolerant()
//								 .skip(Exception.class)
//								 .skipLimit(Integer.MAX_VALUE)
//								 .build();
		
		
		return stepBuilderFactory.get("Simple Step")
								 .<StudentCsv, StudentJson>chunk(3)
								 .reader(flatFileItemReader(null))
								 .processor(flatFileItemProcessor)
								 .writer(jsonFileItemWriter(null))
								 .faultTolerant()
								 //.skip(FlatFileParseException.class)
								 //.skip(NullPointerException.class)
								 .skip(Exception.class)
								 //.skipLimit(3)
								 //.skipLimit(Integer.MAX_VALUE)
								 .skipPolicy(new AlwaysSkipItemSkipPolicy())
								 .listener(skipBadRecordListener)
								 .build();
	}
	
	public Step taskletStep() {
		
		return stepBuilderFactory.get("Tasklet Step")
								 .tasklet(testTask)
								 .build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemReader<StudentCsv> flatFileItemReader(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		
		FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<>();
		
		//flatFileItemReader.setResource(new FileSystemResource(new File("C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\Spring_Batch_Flat_File_ItemReader_N22\\inputFile\\StudentData.csv")));
		flatFileItemReader.setResource(fileSystemResource);
		flatFileItemReader.setName("csvReader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
	}

	private LineMapper<StudentCsv> lineMapper() {
		
		DefaultLineMapper<StudentCsv> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(true);
		delimitedLineTokenizer.setNames("Roll", "First Name", "Last Name", "Email");
		
		BeanWrapperFieldSetMapper<StudentCsv> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(StudentCsv.class);
		
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return lineMapper;
	}
	
	@StepScope
	@Bean
	public JsonFileItemWriter<StudentJson> jsonFileItemWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {
		
		//JsonFileItemWriter<StudentJson> jsonFileItemWriter = new JsonFileItemWriter<StudentJson>(fileSystemResource, new JacksonJsonObjectMarshaller<StudentJson>());
		
		/*
		 * Just simulating NullPointerException for roll 6
		 * 
		 * Note:
		 * ------------------------------------------------
		 * In normal cases we don't write like following. 
		 * In normal scenario, we write above line (144).
		 * ------------------------------------------------
		 */
		JsonFileItemWriter<StudentJson> jsonFileItemWriter = new JsonFileItemWriter<StudentJson>(fileSystemResource, new JacksonJsonObjectMarshaller<StudentJson>()) {

			@Override
			public String doWrite(List<? extends StudentJson> items) {
				
				items.stream().forEach(t -> {
					if(t.getRoll() == 14) {
						throw new NullPointerException("Just simulating NullPointerException for roll:" + t.getRoll());
					}
				});
				
				return super.doWrite(items);
			}
		};
		
		/*
		 * The issue you're encountering, where extra records are printed in the reader's text file 
		 * when an exception occurs in the ItemWriter, is likely due to the way Spring Batch handles 
		 * transactions and rollbacks.
		 *
         * Understanding the Problem
         * ---------------------------
         * 1. Chunk-Oriented Processing:
		 *
		 * In Spring Batch, steps are typically chunk-oriented. Data is read, processed, and written in chunks.
		 * If an exception occurs in the ItemWriter, the transaction for that chunk is rolled back. This means 
		 * that any records read in that chunk will be reprocessed, and the ItemReader will be called again to 
		 * re-read those records.
		 * 
         * 2. Skipping and Rollbacks:
		 *
		 * When an exception occurs in the ItemWriter, the ItemReader will often attempt to re-read the same data 
		 * because the chunk is retried. If you have a SkipListener configured to capture skipped records in the 
		 * ItemReader, it might record the same records again when the ItemReader is re-invoked.
		 * 
		 */
		
		return jsonFileItemWriter;
	}
}
