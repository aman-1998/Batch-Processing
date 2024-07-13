package personal.learning.app.incrementer;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomJobParamIncrementer implements JobParametersIncrementer {

	@Override
	public JobParameters getNext(JobParameters parameters) {
		long runId = 0L;
		JobParameter jobParameter = parameters.getParameters().get("run.id");
		if(jobParameter != null) {
			runId = (long) jobParameter.getValue() + 1L;
		} else {
			runId = 1L;
		}
		
		return new JobParametersBuilder().addLong("run.id", runId)
										 .toJobParameters();
	}

}
