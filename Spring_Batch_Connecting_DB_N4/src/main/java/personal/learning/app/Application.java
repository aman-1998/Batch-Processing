package personal.learning.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Directly run SpringBoot application
 * 
 * When u will run the SpringBoot application for the second time the 
 * job won't get executed as it already got executed once.
 * 
 * Now, after changing job parameters when you will run the SpringBoot application
 * again. Then the job will get executed successfully.
 * Note: You can change job parameter by going to Run Configuration.
 * 
 * (Right Click on project >> Run As >> Spring Boot App)
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
