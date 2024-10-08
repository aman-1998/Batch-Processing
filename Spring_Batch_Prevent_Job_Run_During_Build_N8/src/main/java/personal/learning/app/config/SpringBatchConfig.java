package personal.learning.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	
	@Bean
	public Job  myTransaction() {
		
		return jobBuilderFactory.get("my Job")
								.start(comunicateWithBankStep())
								.next(debitMoneyStep())
								.next(sendSMSStep())
								.build();
	}
	
	public Step comunicateWithBankStep() {
		
		return stepBuilderFactory.get("Step 1 : Communication with receiver's bank")
								 .tasklet(communicateWithBankTask)
								 .build();
	}
	
	public Step debitMoneyStep() {
		
		return stepBuilderFactory.get("Step 2 : Debit money from payer's bank account")
								 .tasklet(debitMoneyTask)
								 .build();
	}
	
	public Step sendSMSStep( ) {
		
		return stepBuilderFactory.get("Step 3 : Send SMS to payer's mobile")
								 .tasklet(sendSMSTask)
								 .build();
	}
	
}
