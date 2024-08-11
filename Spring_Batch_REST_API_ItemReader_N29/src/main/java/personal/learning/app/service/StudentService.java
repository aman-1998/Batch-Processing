package personal.learning.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import personal.learning.app.model.Student;

@Service
public class StudentService {
	
	List<Student> studentList;
	
	public void restCallToGetStudents() {
		RestTemplate restTemplate = new RestTemplate();
		Student[] students = restTemplate.getForObject("http://localhost:8085/student/v1", Student[].class);
		
		studentList = new ArrayList<>();
		
		for(Student st : students) {
			studentList.add(st);
		}
	}
	
	public Student getStudent(long dummy1, String dummy2) {
		System.out.println("Long value = " + dummy1 + " | String value = " + dummy2);
		if(studentList == null) {
			restCallToGetStudents();
		} 
		
		if(studentList != null && !studentList.isEmpty()) {
			return studentList.remove(0);
		}
		
		return null;
	}
}
