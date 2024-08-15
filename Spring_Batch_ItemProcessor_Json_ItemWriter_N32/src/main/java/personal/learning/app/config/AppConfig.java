package personal.learning.app.config;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.processor.JsonItemProcessor;
import personal.learning.app.model.CustomerJdbc;
import personal.learning.app.model.CustomerJson;
import personal.learning.app.tasklet.TestTask;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public ItemProcessor<CustomerJdbc, CustomerJson> jsonItemProcessor() {
		return new JsonItemProcessor();
	}
	
	@Bean
	public Tasklet testTask() {
		return new TestTask();
	}
	
}
