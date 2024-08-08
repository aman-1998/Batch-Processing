package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import personal.learning.app.model.Customer;

public class ConsoleItemWriterForCustomer implements ItemWriter<Customer> {

	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		
		System.out.println("Writing in console using ItemWriter");
		customers.stream().forEach(customer -> System.out.println(customer));
	}
}
