package personal.learning.app.listener.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Before Job : " + jobExecution.getJobInstance().getJobName());
		System.out.println("Job Params : " + jobExecution.getJobParameters());
		System.out.println("Job Execution Context : " + jobExecution.getExecutionContext());
		System.out.println("----------------------------------------------------------------");
		
		jobExecution.getExecutionContext().put("Payer Account No.", "1282837773");
		jobExecution.getExecutionContext().put("Receiver Account No.", "3829928827");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("After Job : " + jobExecution.getJobInstance().getJobName());
		System.out.println("Job Params : " + jobExecution.getJobParameters());
		System.out.println("Job Execution Context : " + jobExecution.getExecutionContext());
		System.out.println("----------------------------------------------------------------");
	}

}
