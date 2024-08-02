package personal.learning.app.item.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ItemReaderForList implements ItemReader<Integer> {
	
	private List<Integer> listOfIntegers = new ArrayList<>();
	int index = 0;
	
	{
		listOfIntegers.add(1);
		listOfIntegers.add(2);
		listOfIntegers.add(3);
		listOfIntegers.add(4);
		listOfIntegers.add(5);
		listOfIntegers.add(6);
		listOfIntegers.add(7);
		listOfIntegers.add(8);
		listOfIntegers.add(9);
		listOfIntegers.add(10);
	}

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		System.out.println("Reading from List using ItemReader");
		Integer item = null;
		if(index < listOfIntegers.size()) {
			item = listOfIntegers.get(index);
			index++;
		} else {
			index = 0;
		}
		return item;
	}
}
