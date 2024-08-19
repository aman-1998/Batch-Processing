package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.custom.exception.CustomValidationException;
import personal.learning.app.model.StudentCsv;
import personal.learning.app.model.StudentJson;

public class SkipBadRecordListener {
	
	@OnSkipInRead
	public void skipInRead(Throwable th) {
		
		if(th instanceof FlatFileParseException) {
			
			String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
							  + "Spring_Batch_FaultTolerance_Retry_Mechanism_N40\\"
							  + "Chunk Job\\SimpleStep\\Reader\\"
							  + "SkipedInReaderBadRecord.txt";
			
			createFile(filePath, ((FlatFileParseException) th).getInput());
		}
	}
	
	@OnSkipInProcess
	public void skipInProcessor(StudentCsv studentCsv, Throwable th) {
		
		if(th instanceof CustomValidationException) {
			
			String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
							  + "Spring_Batch_FaultTolerance_Retry_Mechanism_N40\\"
							  + "Chunk Job\\SimpleStep\\Processor\\"
							  + "SkipedInProcessorBadRecord.txt";
			
			createFile(filePath, "{" + ((CustomValidationException) th).getMessage() + "} " + studentCsv.toString());
		}
	}
	
	@OnSkipInWrite
	public void skipInWriter(StudentJson studentJson, Throwable th) {
		
		String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
						  + "Spring_Batch_FaultTolerance_Retry_Mechanism_N40\\"
						  + "Chunk Job\\SimpleStep\\Writer\\"
						  + "SkipedInWriterBadRecord.txt";
		
		createFile(filePath, studentJson.toString());
	}
	
	public void createFile(String filePath, String data) {
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath, true);
			fileWriter.write("Date : " + LocalDateTime.now() + " >>>>>> " + data + "\n");
		} catch(Exception ex) {
			
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
