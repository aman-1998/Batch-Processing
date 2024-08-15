package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.model.CustomerJdbc;
import personal.learning.app.model.CustomerJson;

public class JsonItemProcessor implements ItemProcessor<CustomerJdbc, CustomerJson> {

	@Override
	public CustomerJson process(CustomerJdbc item) throws Exception {
		
		CustomerJson customerJson = new CustomerJson();
		customerJson.setId(item.getPk());
		customerJson.setFullName(item.getName());
		customerJson.setEmailId(item.getEmail());
		customerJson.setGender(item.getGender());
		customerJson.setLang(item.getLang());
		customerJson.setDob(item.getDob());
		customerJson.setAddress(item.getAddress());
		
		return customerJson;
	}
}
