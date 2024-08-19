package personal.learning.app.validator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import personal.learning.app.model.StudentCsv;

public class Validator {
	
	public ArrayList<String> validate(StudentCsv studentCsv) {
		
		ArrayList<String> errorMessages = new ArrayList<>();
		
		if(!isValidEmail(studentCsv.getEmail())) {
			errorMessages.add("Invalid Email : [" + studentCsv.getEmail() + "]");
		}
		
		return errorMessages;
	}
	
	public boolean isValidEmail(String email) {
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		boolean isValidEmail = matcher.matches();
		
		if(!isValidEmail) {
			return false;
		}
		
		return true;
	}
}
