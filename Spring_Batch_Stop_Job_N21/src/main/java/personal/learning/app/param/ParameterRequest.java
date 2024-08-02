package personal.learning.app.param;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "student")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ParameterRequest {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("standard")
	private String standard;
	
	@JsonProperty("subjects")
	private List<String> subjects = new ArrayList<>();
	
	@JsonProperty("rollNo")
	private int rollNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
}
