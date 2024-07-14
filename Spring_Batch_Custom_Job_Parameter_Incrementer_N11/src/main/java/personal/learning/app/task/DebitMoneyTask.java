package personal.learning.app.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DebitMoneyTask implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
		
		System.out.println("Money debited from payer's bank account");
		return RepeatStatus.FINISHED;
	}
}
