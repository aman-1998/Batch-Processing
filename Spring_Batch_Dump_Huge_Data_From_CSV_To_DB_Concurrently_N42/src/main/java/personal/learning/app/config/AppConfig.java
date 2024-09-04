package personal.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.processor.RepositoryItemProcessorForCustomer;
import personal.learning.app.listener.SkipBadRecordListenerForCustomer;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public RepositoryItemProcessorForCustomer repositoryItemProcessorForCustomer() {
		return new RepositoryItemProcessorForCustomer();
	}
	
	@Bean
	public SkipBadRecordListenerForCustomer skipBadRecordListenerForCustomer() {
		return new SkipBadRecordListenerForCustomer();
	}
}
