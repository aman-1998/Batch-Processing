package personal.learning.app.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SendSMSTask implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
		
		System.out.println("Send SMS to payer's mobile");
		return RepeatStatus.FINISHED;
	}
}
