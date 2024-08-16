package personal.learning.app.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder({"successCode", 
					"successMessage"})
public class SuccessResponse {
	
	@JsonProperty("successCode")
	private String successCode;
	
	@JsonProperty("successMessage")
	private String successMessage;

	public SuccessResponse(String successCode, String successMessage) {
		this.successCode = successCode;
		this.successMessage = successMessage;
	}
	
	public String getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@Override
	public String toString() {
		return "SuccessResponse [successCode=" + successCode + ", successMessage=" + successMessage + "]";
	}
}
