package personal.learning.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.app.model.Student;

@RestController
@RequestMapping(value="/student/v1")
public class JobController {
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> startJob() {
		
		Student student1 = new Student();
		student1.setFirstName("Aman");
		student1.setLastName("Mishra");
		student1.setRollNo(137);
		student1.setStandard("X");
		student1.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English"));
		
		Student student2 = new Student();
		student2.setFirstName("Pooja");
		student2.setLastName("Upadhyay");
		student2.setRollNo(138);
		student2.setStandard("X");
		student2.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English", "Hindi"));
		
		Student student3 = new Student();
		student3.setFirstName("Geetha");
		student3.setLastName("Bhardwaj");
		student3.setRollNo(139);
		student3.setStandard("X");
		student3.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English", "Physical Ed."));
		
		Student student4 = new Student();
		student4.setFirstName("Yash");
		student4.setLastName("Saxena");
		student4.setRollNo(140);
		student4.setStandard("X");
		student4.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English"));
		
		Student student5 = new Student();
		student5.setFirstName("Nikhil");
		student5.setLastName("Mehra");
		student5.setRollNo(141);
		student5.setStandard("X");
		student5.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English", "Hindi"));
		
		Student student6 = new Student();
		student6.setFirstName("Shruti");
		student6.setLastName("Srivastav");
		student6.setRollNo(142);
		student6.setStandard("X");
		student6.setSubjects(Arrays.asList("Math", "Physics", "Chemistry", "English", "Music"));
		
		List<Student> studentList = Arrays.asList(student1, student2, student3, student4, student5, student6);
		
		return ResponseEntity.ok().body(studentList);
	}
}
