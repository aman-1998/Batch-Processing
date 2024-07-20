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
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
/*
 * Directly run SpringBoot application
 * (Right Click on project >> Run As >> Spring Boot App)
 * 
 * When u will run the SpringBoot application for the second time the 
 * job won't get executed as it already got executed once.
 * 
 * Now, after changing job parameters when you will run the SpringBoot application
 * again. Then the job will get executed successfully.
 * Note: You can change job parameter by going to Run Configuration.
 * 
 * The "spring.batch.job.enabled=false" property prevents jobs from being run automatically 
 * when the application starts, but it won't prevent job beans from being created or prevent 
 * jobs from running during the build process. So, to prevent job from running during build 
 * we use command-line arguments.
 * 
 * We can package the application in jar format by building and then run the command:-
 * java -jar Spring_Batch_Using_CommandLineRunner_N6-0.0.1-SNAPSHOT.jar
 * This command has to be run from inside the target folder.
 * 
 * Incrementer increases the value to job Parameter internally and hence making job parameter unique.
 * So, even if we run the application again and again. Every time it will run.
 * 
 * If we want to use CustomJobParamIncrementer then we need to use SpringBoot's auto configuration
 * mechanism to run job. If we don't want to use SpringBoot's auto configuration mechanism then we
 * need to manually increment the parameters (example using System.currentTimeMillis()).
 * 
 */
@SpringBootApplication
public class Application {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job myJob;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		
//		return new CommandLineRunner() {
//			
//			@Override
//			public void run(String... args) throws Exception {
//				
//				JobParameters jobParameters = new JobParametersBuilder().addString("Credit Bank Name", "SBI")
//										  .addString("Debit Bank Name", "PNB")
//										  .toJobParameters();
//				
//				JobExecution jobexecution = jobLauncher.run(myJob, jobParameters);
//				
//				System.out.println("Job status : " + jobexecution.getStatus());
//			}
//		};
//	}
	
	@Bean
	@Order(1)
	public CommandLineRunner firstJobRunner() {
		
		return args -> {
			
			System.out.println("Running first job:-");
			
			JobParameters jobParameters = new JobParametersBuilder().addString("Credit Bank", "HDFC Bank")
																	.addString("Debit Bank", "IDFC FIRST Bank")
																	.addLong("run.id", System.currentTimeMillis())
																	.toJobParameters();

			JobExecution jobexecution = jobLauncher.run(myJob, jobParameters);

			System.out.println("Job status : " + jobexecution.getStatus());
		};
	}
	
	@Bean
	@Order(2)
	public CommandLineRunner secondJobRunner() {
		
		return args -> {
			
			System.out.println("Running second job:-");
			
			JobParameters jobParameters = new JobParametersBuilder().addString("Credit Bank", "Canra Bank")
																	.addString("Debit Bank", "Union Bank")
																	.addLong("run.id", System.currentTimeMillis())
																	.toJobParameters();

			JobExecution jobexecution = jobLauncher.run(myJob, jobParameters);

			System.out.println("Job status : " + jobexecution.getStatus());
		};
	}

}
