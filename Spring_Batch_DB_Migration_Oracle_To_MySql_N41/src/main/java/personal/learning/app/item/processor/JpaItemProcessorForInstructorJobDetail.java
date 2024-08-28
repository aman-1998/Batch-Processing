package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.InstructorJobDetailMsq;
import personal.learning.app.oracle.entity.InstructorJobDetailOra;

public class JpaItemProcessorForInstructorJobDetail implements ItemProcessor<InstructorJobDetailOra, InstructorJobDetailMsq>{

	@Override
	public InstructorJobDetailMsq process(InstructorJobDetailOra item) throws Exception {
		
		InstructorJobDetailMsq instructorJobDetailMsq = new InstructorJobDetailMsq();
		instructorJobDetailMsq.setId(item.getId());
		instructorJobDetailMsq.setOrganization(item.getOrganization());
		instructorJobDetailMsq.setOfficeAddress(item.getOfficeAddress());
		instructorJobDetailMsq.setOfficeHrPhoneNo(item.getOfficeHrPhoneNo());
		instructorJobDetailMsq.setOfficeHrEmail(item.getOfficeHrEmail());
		instructorJobDetailMsq.setInstructorId(item.getInstructorId());
		
		return instructorJobDetailMsq;
	}

}
