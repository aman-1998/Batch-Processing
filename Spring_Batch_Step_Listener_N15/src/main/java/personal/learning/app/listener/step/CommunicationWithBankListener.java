package personal.learning.app.listener.step;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CommunicationWithBankListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Before Step 1 : " + stepExecution.getStepName());
		System.out.println("Job Params : " + stepExecution.getJobParameters());
		System.out.println("Step Execution Context : " + stepExecution.getExecutionContext());
		System.out.println("Job Execution Context : " + stepExecution.getJobExecution().getExecutionContext());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		stepExecution.getExecutionContext().put("Communication", "Two banks are communicating");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After Step 1 : " + stepExecution.getStepName());
		System.out.println("Job Params : " + stepExecution.getJobParameters());
		System.out.println("Step Execution Context : " + stepExecution.getExecutionContext());
		System.out.println("Job Execution Context : " + stepExecution.getJobExecution().getExecutionContext());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return ExitStatus.COMPLETED;
	}

}
