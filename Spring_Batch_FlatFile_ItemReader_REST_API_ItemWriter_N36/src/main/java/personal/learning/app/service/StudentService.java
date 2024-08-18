package personal.learning.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import personal.learning.app.model.Student;
import personal.learning.app.response.StudentResponse;

@Service
public class StudentService {
	
	public StudentResponse restCallToCreateStudent(Student student) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.postForObject("http://localhost:8085/createStudent", student, StudentResponse.class);
	}
}
