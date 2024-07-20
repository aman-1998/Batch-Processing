package personal.learning.app.listener.step;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class SendSMSListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Before Step 3 : " + stepExecution.getStepName());
		System.out.println("Step Execution Context : " + stepExecution.getExecutionContext());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		stepExecution.getExecutionContext().put("SMS", "Sending SMS");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After Step 3 : " + stepExecution.getStepName());
		System.out.println("Step Execution Context : " + stepExecution.getExecutionContext());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return ExitStatus.COMPLETED;
	}

}
