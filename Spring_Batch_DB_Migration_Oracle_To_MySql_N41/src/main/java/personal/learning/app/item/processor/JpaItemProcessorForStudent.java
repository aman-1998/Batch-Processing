package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.StudentMsq;
import personal.learning.app.oracle.entity.StudentOra;

public class JpaItemProcessorForStudent implements ItemProcessor<StudentOra, StudentMsq> {

	@Override
	public StudentMsq process(StudentOra item) throws Exception {
		
		StudentMsq studentMsq = new StudentMsq();
		
		studentMsq.setId(item.getId());
		studentMsq.setRoll(item.getRoll());
		studentMsq.setStudentFirstName(item.getStudentFirstName());
		studentMsq.setStudentLastName(item.getStudentLastName());
		studentMsq.setStudentEmail(item.getStudentEmail());
		
		return studentMsq;
	}

}
