package personal.learning.app.item.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

public class ItemProcessorForList implements ItemProcessor<Integer, String> {

	@Override
	public String process(Integer item) throws Exception {
		
		System.out.println("Processing item using ItemProcessor");
		String output = StringUtils.EMPTY;
		switch(item) {
			case 1:
				output = "One";
				break;
			case 2:
				output = "Two";
				break;
			case 3:
				output = "Three";
				break;
			case 4:
				output = "Four";
				break;
			case 5:
				output = "Five";
				break;
			case 6:
			    output = "Six";
			    break;
			case 7:
				output = "Seven";
				break;
			case 8:
				output = "Eight";
				break;
			case 9:
				output = "Nine";
				break;
			case 10:
				output = "Ten";
				break;
			default:
				output = "Invalid";	
		}
		
		return output;
	}
	
	
}
