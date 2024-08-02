package personal.learning.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.app.param.ParameterRequest;

@RestController
@RequestMapping(value="/job")
public class JobController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job myJob;
	
	@Autowired
	private Job myPracticeJob;
	
	@Autowired
	private Job myTestingJob;
	
	@GetMapping(value = "/start/{jobName}")
	public ResponseEntity<Object> startJob(@PathVariable("jobName") String jobName) {
		
		if(StringUtils.equals(jobName, "TestJob")) {
//			JobParameters jobParameters = new JobParametersBuilder().addString("Car", "Thar")
//																	.addString("Brand", "Mahindra")
//																	.addLong("currentMillis", System.currentTimeMillis())
//																	.toJobParameters();
			
			Map<String, JobParameter> params = new HashMap<>();
			params.put("Car", new JobParameter("Thar"));
			params.put("Brand", new JobParameter("Mahindra"));
			params.put("currentMillis", new JobParameter(System.currentTimeMillis()));
			
			JobParameters jobParameters = new JobParameters(params);
			
			try {
				JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
				System.out.println("Job Status : " + jobExecution.getStatus());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return ResponseEntity.ok("Job TestJob Started...");
			
		} else if(StringUtils.equals(jobName, "PracticeJob")) {
			
			JobParameters jobParameters = new JobParametersBuilder().addString("Animal", "Cow")
																	.addString("Human", "Aman")
																	.addLong("currentMillis", System.currentTimeMillis())
																	.toJobParameters();
			
			try {
				JobExecution jobExecution = jobLauncher.run(myPracticeJob, jobParameters);
				System.out.println("Job Status : " + jobExecution.getStatus());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return ResponseEntity.ok("Job PracticeJob Started...");
		}
		
		return ResponseEntity.badRequest().body("Invalid Job");
	}
	
	@PostMapping(value = "/startwithparam/{jobName}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> startJobWithParameter(@PathVariable("jobName") String jobName, 
														@RequestBody ParameterRequest parameterRequest) {
		
		if(StringUtils.equals(jobName, "TestingJob")) {
			
			Map<String, JobParameter> params = new HashMap<>();
			params.put("name", new JobParameter(parameterRequest.getName()));
			params.put("standard", new JobParameter(parameterRequest.getStandard()));
			params.put("rollNo", new JobParameter(String.valueOf(parameterRequest.getRollNo())));
			params.put("currentMillis", new JobParameter(System.currentTimeMillis()));
			
			JobParameters jobParameters = new JobParameters(params);
			
			parameterRequest.getSubjects().stream().forEach(t -> System.out.println(t));
			
			try {
				JobExecution jobExecution = jobLauncher.run(myTestingJob, jobParameters);
				System.out.println("Job Status : " + jobExecution.getStatus());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return ResponseEntity.ok("Job TestingJob Started");
		}
		
		return ResponseEntity.badRequest().body("Invalid Job");
	}
	
}
