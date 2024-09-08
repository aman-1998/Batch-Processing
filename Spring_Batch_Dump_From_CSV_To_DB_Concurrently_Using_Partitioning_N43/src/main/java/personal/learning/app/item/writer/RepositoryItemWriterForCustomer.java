package personal.learning.app.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import personal.learning.app.entity.CustomerDb;
import personal.learning.app.repository.CustomerRepository;

public class RepositoryItemWriterForCustomer implements ItemWriter<CustomerDb> {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void write(List<? extends CustomerDb> items) throws Exception {
		
		System.out.println("Thread name : " + Thread.currentThread().getName());
		customerRepository.saveAll(items);
	}

}
