package personal.learning.app.config;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.writer.ConsoleItemWriterForStudent;
import personal.learning.app.model.Student;
import personal.learning.app.tasklet.TestTask;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public ItemWriter<Student> consoleItemWriterForStudent() {
		return new ConsoleItemWriterForStudent();
	}
	
	@Bean
	public Tasklet testTask() {
		return new TestTask();
	}
	
}
