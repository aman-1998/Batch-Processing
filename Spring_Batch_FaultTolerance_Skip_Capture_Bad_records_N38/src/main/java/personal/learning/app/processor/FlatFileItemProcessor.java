package personal.learning.app.processor;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import personal.learning.app.custom.exception.CustomValidationException;
import personal.learning.app.model.StudentCsv;
import personal.learning.app.model.StudentJson;
import personal.learning.app.validator.Validator;

public class FlatFileItemProcessor implements ItemProcessor<StudentCsv, StudentJson>{
	
	@Autowired
	private Validator validator;

	@Override
	public StudentJson process(StudentCsv item) throws Exception {
		
		ArrayList<String> errorMessages = validator.validate(item);
		if(!errorMessages.isEmpty()) {
			throw new CustomValidationException(String.join(",", errorMessages));
		}
		
		StudentJson studentJson = new StudentJson();
		studentJson.setRoll(item.getRoll());
		
		String lastName = StringUtils.isNotBlank(item.getLastName()) ? " " + item.getLastName() : StringUtils.EMPTY;
		studentJson.setFullName(item.getFirstName() + lastName);
		
		studentJson.setEmailId(item.getEmail());
		studentJson.setSchool("Kanchrapara Harnett English Medium School");
		return studentJson;
	}

}
