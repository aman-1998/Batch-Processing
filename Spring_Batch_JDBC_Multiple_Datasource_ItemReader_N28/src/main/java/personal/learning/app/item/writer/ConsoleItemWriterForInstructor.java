package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import personal.learning.app.model.Instructor;

public class ConsoleItemWriterForInstructor implements ItemWriter<Instructor> {

	@Override
	public void write(List<? extends Instructor> instructors) throws Exception {
		
		System.out.println("Writing in console using ItemWriter");
		instructors.stream().forEach(instructor -> System.out.println(instructor));
	}
}
