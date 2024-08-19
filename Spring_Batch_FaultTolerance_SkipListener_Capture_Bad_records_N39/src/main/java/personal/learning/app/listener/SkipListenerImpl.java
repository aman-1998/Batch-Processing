package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.custom.exception.CustomValidationException;
import personal.learning.app.model.StudentCsv;
import personal.learning.app.model.StudentJson;

public class SkipListenerImpl implements SkipListener<StudentCsv, StudentJson> {

	@Override
	public void onSkipInRead(Throwable th) {
		
			if(th instanceof FlatFileParseException) {
						
				String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
								  + "Spring_Batch_FaultTolerance_SkipListener_Capture_Bad_records_N39\\"
								  + "Chunk Job\\SimpleStep\\Reader\\"
								  + "SkipedInReaderBadRecord.txt";
				
				createFile(filePath, ((FlatFileParseException) th).getInput());
			}
	}

	@Override
	public void onSkipInWrite(StudentJson studentJson, Throwable th) {
		
		String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
						  + "Spring_Batch_FaultTolerance_SkipListener_Capture_Bad_records_N39\\"
						  + "Chunk Job\\SimpleStep\\Writer\\"
						  + "SkipedInWriterBadRecord.txt";

		createFile(filePath, studentJson.toString());
	}

	@Override
	public void onSkipInProcess(StudentCsv studentCsv, Throwable th) {
		
		if(th instanceof CustomValidationException) {
			
			String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
							  + "Spring_Batch_FaultTolerance_SkipListener_Capture_Bad_records_N39\\"
							  + "Chunk Job\\SimpleStep\\Processor\\"
							  + "SkipedInProcessorBadRecord.txt";
		
			createFile(filePath, "{" + ((CustomValidationException) th).getMessage() + "} " + studentCsv.toString());
		}
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
