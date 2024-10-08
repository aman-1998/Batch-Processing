package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ItemWriterForList implements ItemWriter<Integer>{

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		
		System.out.println("Writing in console using ItemWriter");
		items.stream().forEach(item -> System.out.println(item));
	}

}
