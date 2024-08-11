package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import personal.learning.app.model.Student;

public class ConsoleItemWriterForStudent implements ItemWriter<Student> {

	@Override
	public void write(List<? extends Student> students) throws Exception {
		
		System.out.println("Writing in console using ItemWriter");
		students.stream().forEach(student -> System.out.println(student));
	}
}
