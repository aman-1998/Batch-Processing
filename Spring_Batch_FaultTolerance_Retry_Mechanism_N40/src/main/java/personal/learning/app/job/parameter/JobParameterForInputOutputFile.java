package personal.learning.app.job.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("inputOutputFile")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JobParameterForInputOutputFile {
	
	@JsonProperty("inputFileLocation")
	String inputFileLocation;
	
	@JsonProperty("outputFileLocation")
	String outputFileLocation;
	
	public JobParameterForInputOutputFile() { }
	
	public JobParameterForInputOutputFile(String inputFileLocation, String outpFileLocation) {
		this.inputFileLocation = inputFileLocation;
		this.outputFileLocation = outpFileLocation;
	}

	public String getInputFileLocation() {
		return inputFileLocation;
	}

	public void setInputFileLocation(String inputFileLocation) {
		this.inputFileLocation = inputFileLocation;
	}

	public String getOutputFileLocation() {
		return outputFileLocation;
	}

	public void setOutputFileLocation(String outputFileLocation) {
		this.outputFileLocation = outputFileLocation;
	}

	@Override
	public String toString() {
		return "JobParameterForInputFile [inputFileLocation=" + inputFileLocation 
				+ ", outputFileLocation=" + outputFileLocation + "]";
	}
}
