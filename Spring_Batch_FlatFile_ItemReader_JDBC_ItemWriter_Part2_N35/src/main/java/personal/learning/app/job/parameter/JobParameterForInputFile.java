package personal.learning.app.job.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("inputfile")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JobParameterForInputFile {
	
	@JsonProperty("inputFileLocation")
	String inputFileLocation;
	
	public JobParameterForInputFile() { }
	
	public JobParameterForInputFile(String inputFileLocation) {
		this.inputFileLocation = inputFileLocation;
	}

	public String getInputFileLocation() {
		return inputFileLocation;
	}

	public void setInputFileLocation(String inputFileLocation) {
		this.inputFileLocation = inputFileLocation;
	}

	@Override
	public String toString() {
		return "JobParameterForInputFile [inputFileLocation=" + inputFileLocation + "]";
	}

}
