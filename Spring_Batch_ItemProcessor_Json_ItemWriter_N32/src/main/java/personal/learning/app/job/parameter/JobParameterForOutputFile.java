package personal.learning.app.job.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("outputfile")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JobParameterForOutputFile {
	
	@JsonProperty("outputFileLocation")
	String outputFileLocation;

	public String getOutputFileLocation() {
		return outputFileLocation;
	}

	public void setOutputFileLocation(String outputFileLocation) {
		this.outputFileLocation = outputFileLocation;
	}

	@Override
	public String toString() {
		return "JobParameterForOutputFile [outputFileLocation=" + outputFileLocation + "]";
	}
}
