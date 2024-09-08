package personal.learning.app.item.processor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.entity.CustomerCsv;
import personal.learning.app.entity.CustomerDb;

public class RepositoryItemProcessorForCustomer implements ItemProcessor<CustomerCsv, CustomerDb> {

	@Override
	public CustomerDb process(CustomerCsv item) throws Exception {
		
		CustomerDb customerDb = new CustomerDb();
		
		customerDb.setId(item.getId());
		customerDb.setFirstName(item.getFirstName());
		customerDb.setLastName(item.getLastName());
		customerDb.setEmail(item.getEmail());
		customerDb.setGender(item.getGender());
		customerDb.setContactNo(item.getContactNo());
		customerDb.setCountry(item.getCountry());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		customerDb.setDob(formatter.parse(item.getDob()));
		
		return customerDb;
	}

}
