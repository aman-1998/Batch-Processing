package personal.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.processor.RepositoryItemProcessorForCustomer;
import personal.learning.app.item.writer.RepositoryItemWriterForCustomer;
import personal.learning.app.listener.SkipBadRecordListenerForCustomer;
import personal.learning.app.partitioner.CsvRowPartitioner;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public RepositoryItemWriterForCustomer repositoryItemWriterForCustomer() {
		return new RepositoryItemWriterForCustomer();
	}
	
	@Bean
	public RepositoryItemProcessorForCustomer repositoryItemProcessorForCustomer() {
		return new RepositoryItemProcessorForCustomer();
	}
	
	@Bean
	public SkipBadRecordListenerForCustomer skipBadRecordListenerForCustomer() {
		return new SkipBadRecordListenerForCustomer();
	}
	
	@Bean
	public CsvRowPartitioner csvRowPartitioner() {
		return new CsvRowPartitioner();
	}
}
