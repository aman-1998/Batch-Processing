package personal.learning.app.config;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.listener.job.JobListener;
import personal.learning.app.listener.step.CommunicationWithBankListener;
import personal.learning.app.listener.step.DebitMoneyListener;
import personal.learning.app.listener.step.SendSMSListener;
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
	
	@Bean
	public StepExecutionListener communicationWithBankListener() {
		return new CommunicationWithBankListener();
	}
	
	@Bean
	public StepExecutionListener debitMoneyListener() {
		return new DebitMoneyListener();
	}
	
	@Bean
	public StepExecutionListener sendSMSListener() {
		return new SendSMSListener();
	}
}
