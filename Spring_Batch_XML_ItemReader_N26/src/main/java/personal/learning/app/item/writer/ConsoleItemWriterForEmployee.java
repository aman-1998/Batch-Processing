package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import personal.learning.app.model.Employee;

public class ConsoleItemWriterForEmployee implements ItemWriter<Employee> {

	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		
		System.out.println("Writing in console using ItemWriter");
		employees.stream().forEach(employee -> System.out.println(employee));
	}
}
