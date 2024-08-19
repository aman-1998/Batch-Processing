package personal.learning.app.config;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.listener.SkipListenerImpl;
import personal.learning.app.processor.FlatFileItemProcessor;
import personal.learning.app.tasklet.TestTask;
import personal.learning.app.validator.Validator;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public Tasklet testTask() {
		return new TestTask();
	}
	
	@Bean
	public FlatFileItemProcessor flatFileItemProcessor() {
		return new FlatFileItemProcessor();
	}
	
	@Bean
	public SkipListenerImpl skipListenerImpl() {
		return new SkipListenerImpl();
	}
	
	@Bean
	public Validator validator() {
		return new Validator();
	}
}
