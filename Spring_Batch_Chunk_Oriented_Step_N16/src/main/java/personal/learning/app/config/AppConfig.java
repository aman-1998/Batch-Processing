package personal.learning.app.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.item.processor.ItemProcessorForList;
import personal.learning.app.item.reader.ItemReaderForList;
import personal.learning.app.item.writer.ItemWriterForList;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public ItemReader<Integer> itemReaderForList() {
		return new ItemReaderForList();
	}
	
	@Bean
	public ItemProcessor<Integer, String> itemProcessorForList() {
		return new ItemProcessorForList();
	}
	
	@Bean
	public ItemWriter<String> itemWriterForList() {
		return new ItemWriterForList();
	}
}
