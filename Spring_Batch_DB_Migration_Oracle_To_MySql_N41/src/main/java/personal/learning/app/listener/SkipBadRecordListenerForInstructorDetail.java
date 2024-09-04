package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.mysql.entity.InstructorDetailMsq;
import personal.learning.app.oracle.entity.InstructorDetailOra;

public class SkipBadRecordListenerForInstructorDetail {
	
	@OnSkipInRead
    public void skipInRead(Throwable th) {
		String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Reader\\"
				  + "SkipedInReaderBadRecord.txt";

		String errorMsg = (th instanceof FlatFileParseException) 
						  ? ((FlatFileParseException) th).getInput() 
						  : th.getMessage();
		createFile(filePath, "Error in reading from INSTRUCTOR_DETAIL table in Oracle DB: " + errorMsg);
    }

    @OnSkipInProcess
    public void skipInProcessor(InstructorDetailOra instructorDetailOra, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Processor\\"
				  + "SkipedInProcessorBadRecord.txt";

		createFile(filePath, "Error in processing row from INSTRUCTOR_DETAIL table in Oracle DB:  " 
								+ instructorDetailOra.toString() + "\nException: " + th.getMessage());
    }

    @OnSkipInWrite
    public void skipInWriter(InstructorDetailMsq instructorDetailMsq, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Writer\\"
				  + "SkipedInWriterBadRecord.txt";
  	
        createFile(filePath, "Error in Writing to INSTRUCTOR_DETAIL table in MySql DB: " + instructorDetailMsq.toString() 
        						+ "\nException: " + th.getMessage());
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
