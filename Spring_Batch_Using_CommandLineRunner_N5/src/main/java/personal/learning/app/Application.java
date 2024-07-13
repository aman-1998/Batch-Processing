package personal.learning.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Directly run SpringBoot application
 * 
 * When u will run the SpringBoot application for the second time the 
 * job won't get executed as it already got executed once.
 * 
 * Now, after changing job parameters when you will run the SpringBoot application
 * again. Then the job will get executed successfully.
 * Note: You can change job parameter by going to Run Configuration.
 * 
 * (Right Click on project >> Run As >> Spring Boot App)
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job myJob;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		JobParameters jobParameters = new JobParametersBuilder().addString("Credit Bank Name", "YES Bank")
								  .addString("Debit Bank Name", "DBS Bank")
								  .toJobParameters();
		
		JobExecution jobexecution = jobLauncher.run(myJob, jobParameters);
		
		System.out.println("Job status : " + jobexecution.getStatus());
	}

}
