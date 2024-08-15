package personal.learning.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.app.job.parameter.JobParameterForInputFile;
import personal.learning.app.response.ErrorResponse;
import personal.learning.app.response.SuccessResponse;

@RestController
@RequestMapping(value="/job")
public class JobController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job myJob;
	
	@Autowired
	private JobOperator jobOperator;
	
	@PostMapping(value = "/start/{jobName}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> startJob(@PathVariable("jobName") String jobName, @RequestBody JobParameterForInputFile jobParameterForInputFile) {
		
		if(StringUtils.equals(jobName, "TestJob")) {
//			JobParameters jobParameters = new JobParametersBuilder().addString("Car", "Thar")
//																	.addString("Brand", "Mahindra")
//																	.addLong("currentMillis", System.currentTimeMillis())
//																	.toJobParameters();
			
			Map<String, JobParameter> params = new HashMap<>();
			params.put("inputFile", new JobParameter(jobParameterForInputFile.getInputFileLocation()));
			params.put("currentMillis", new JobParameter(System.currentTimeMillis()));
			
			JobParameters jobParameters = new JobParameters(params);
			
			try {
				JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
				System.out.println("Job Status : " + jobExecution.getStatus());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			SuccessResponse successResponse = new SuccessResponse("SUCCESS", "Job TestJob Started...");
			return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
			
		}
		
		ErrorResponse errorResponse = new ErrorResponse("INVALID JOB", "No job found with name : " + jobName);
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@RequestMapping(value = "/stop/{jobExecutionId}", method = RequestMethod.GET)
	public ResponseEntity<Object> stopJob(@PathVariable("jobExecutionId") Long jobExecutionId) {
		try {
			jobOperator.stop(jobExecutionId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SuccessResponse successResponse = new SuccessResponse("JOB STOPPED", "Job stopped with job_execution_id : " + jobExecutionId);
		return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
	}
}
