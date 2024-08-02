package personal.learning.app.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class JobScheduler {
	
	@Autowired
	private Job myJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void startJob() {
		
		JobParameters jobParameters = new JobParametersBuilder().addString("brand", "Jeep")
																.addString("model", "Jeep Meridian")
																.addLong("currentMillis", System.currentTimeMillis())
																.toJobParameters();
		
		try {
			JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
			System.out.println("Job Status : " + jobExecution.getStatus());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
