package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import personal.learning.app.listener.step.CommunicationWithBankListener;
import personal.learning.app.listener.step.DebitMoneyListener;
import personal.learning.app.listener.step.SendSMSListener;
import personal.learning.app.task.CommunicateWithBankTask;
import personal.learning.app.task.DebitMoneyTask;
import personal.learning.app.task.SendSMSTask;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private CommunicateWithBankTask communicateWithBankTask;
	
	@Autowired
	private DebitMoneyTask debitMoneyTask;
	
	@Autowired
	private SendSMSTask sendSMSTask;
	
	@Autowired
	private JobExecutionListener jobexecutionListener;
	
	@Autowired
	private StepExecutionListener communicationWithBankListener;
	
	@Autowired
	private StepExecutionListener debitMoneyListener;
	
	@Autowired
	private StepExecutionListener sendSMSListener;
	
	@Bean
	public Job  myTransaction() {
		
		return jobBuilderFactory.get("my Job")
								.start(comunicateWithBankStep())
								.next(debitMoneyStep())
								.next(sendSMSStep())
								.listener(jobexecutionListener)
								.build();
	}
	
	public Step comunicateWithBankStep() {
		
		return stepBuilderFactory.get("Communication with receiver's bank")
								 .tasklet(communicateWithBankTask)
								 .listener(communicationWithBankListener)
								 .build();
	}
	
	public Step debitMoneyStep() {
		
		return stepBuilderFactory.get("Debit money from payer's bank account")
								 .tasklet(debitMoneyTask)
								 .listener(debitMoneyListener)
								 .build();
	}
	
	public Step sendSMSStep( ) {
		
		return stepBuilderFactory.get("Send SMS to payer's mobile")
								 .tasklet(sendSMSTask)
								 .listener(sendSMSListener)
								 .build();
	}
	
}
