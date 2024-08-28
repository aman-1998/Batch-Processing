package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.InstructorDetailMsq;
import personal.learning.app.oracle.entity.InstructorDetailOra;

public class JpaItemProcessorForInstructorDetail implements ItemProcessor<InstructorDetailOra, InstructorDetailMsq> {

	@Override
	public InstructorDetailMsq process(InstructorDetailOra item) throws Exception {
		
		InstructorDetailMsq instructorDetailMsq = new InstructorDetailMsq();
		
		instructorDetailMsq.setId(item.getId());
		instructorDetailMsq.setYoutubeChannel(item.getYoutubeChannel());
		instructorDetailMsq.setChannelDescription(item.getChannelDescription());
		instructorDetailMsq.setNoOfSubscribers(item.getNoOfSubscriber());
		instructorDetailMsq.setInstructorId(item.getInstructorId());
		
		return instructorDetailMsq;
	}

}
