package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.InstructorMsq;
import personal.learning.app.oracle.entity.InstructorOra;

public class JpaItemProcessorForInstructor implements ItemProcessor<InstructorOra, InstructorMsq> {

	@Override
	public InstructorMsq process(InstructorOra item) throws Exception {
		
		InstructorMsq instructorMsq = new InstructorMsq();
		
		instructorMsq.setId(item.getId());
		instructorMsq.setInstructorFirstName(item.getInstructorFirstName());
		instructorMsq.setInstructorLastName(item.getInstructorLastName());
		instructorMsq.setInstructorEmail(item.getInstructorEmail());
		
		return instructorMsq;
	}

}
