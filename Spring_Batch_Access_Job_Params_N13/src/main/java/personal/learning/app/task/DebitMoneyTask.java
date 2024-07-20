package personal.learning.app.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DebitMoneyTask implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
		
		System.out.println("============> Money debited from payer's bank account <============");
		String creditBank = (String) contribution.getStepExecution()
												 .getJobParameters()
												 .getParameters()
												 .get("Credit Bank")
												 .getValue();
		System.out.println("Credit Bank = " + creditBank);
		
		String debitBank = (String) contribution.getStepExecution()
												.getJobParameters()
												.getParameters()
												.get("Debit Bank")
												.getValue();
		System.out.println("Debit Bank = " + debitBank);
		
		Long runId = (Long) contribution.getStepExecution()
										.getJobParameters()
										.getParameters()
										.get("run.id")
										.getValue();
		
		System.out.println("Run Id = " + runId);
		return RepeatStatus.FINISHED;
	}
}
