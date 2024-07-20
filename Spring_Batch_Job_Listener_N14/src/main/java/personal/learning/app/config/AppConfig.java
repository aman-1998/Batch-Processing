package personal.learning.app.config;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.listener.JobListener;
import personal.learning.app.task.CommunicateWithBankTask;
import personal.learning.app.task.DebitMoneyTask;
import personal.learning.app.task.SendSMSTask;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public CommunicateWithBankTask communicateWithBankTask() {
		return new CommunicateWithBankTask();
	}
	
	@Bean
	public DebitMoneyTask debitMoneyTask() {
		return new DebitMoneyTask();
	}
	
	@Bean
	public SendSMSTask sendSMSTask() {
		return new SendSMSTask();
	}
	
	@Bean
	public JobExecutionListener jobExecutionListener() {
		return new JobListener();
	}
}
