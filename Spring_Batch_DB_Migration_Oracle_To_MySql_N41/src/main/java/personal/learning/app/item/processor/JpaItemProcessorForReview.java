package personal.learning.app.item.processor;

import org.springframework.batch.item.ItemProcessor;

import personal.learning.app.mysql.entity.ReviewMsq;
import personal.learning.app.oracle.entity.ReviewOra;

public class JpaItemProcessorForReview implements ItemProcessor<ReviewOra, ReviewMsq> {

	@Override
	public ReviewMsq process(ReviewOra item) throws Exception {
		
		ReviewMsq reviewMsq = new ReviewMsq();
		
		reviewMsq.setId(item.getId());
		reviewMsq.setComments(item.getComments());
		reviewMsq.setCourseId(item.getCourseId());
		
		return reviewMsq;
	}

}
