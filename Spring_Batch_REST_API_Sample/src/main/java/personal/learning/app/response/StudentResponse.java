package personal.learning.app.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentResponse {
	
	@JsonProperty("httpStatus")
	public HttpStatus httpStatus;
	
	@JsonProperty("message")
	public String message;
	
	public StudentResponse() {}

	public StudentResponse(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "StudentResponse [httpStatus=" + httpStatus + ", message=" + message + "]";
	}
	
}
