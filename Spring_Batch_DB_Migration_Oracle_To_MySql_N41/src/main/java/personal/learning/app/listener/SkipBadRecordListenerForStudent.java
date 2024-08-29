package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.mysql.entity.ReviewMsq;
import personal.learning.app.mysql.entity.StudentMsq;
import personal.learning.app.oracle.entity.ReviewOra;
import personal.learning.app.oracle.entity.StudentOra;

public class SkipBadRecordListenerForStudent {
	
	@OnSkipInRead
    public void skipInRead(Throwable th) {
		String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Reader\\"
				  + "SkipedInReaderBadRecord.txt";

		String errorMsg = (th instanceof FlatFileParseException) 
		  ? ((FlatFileParseException) th).getInput() 
		  : th.getMessage();
		createFile(filePath, "Error in reading from STUDENT table in Oracle DB: " + errorMsg);
    }

    @OnSkipInProcess
    public void skipInProcessor(StudentOra studentOra, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Processor\\"
				  + "SkipedInProcessorBadRecord.txt";

		createFile(filePath, "Error in processing row from STUDENT table in Oracle DB:  " 
								+ studentOra.toString() + "\nException: " + th.getMessage());
    }

    @OnSkipInWrite
    public void skipInWriter(StudentMsq studentMsq, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_DB_Migration_Oracle_To_MySql_N41\\"
				  + "Chunk Job\\SimpleStep\\Writer\\"
				  + "SkipedInWriterBadRecord.txt";
  	
        createFile(filePath, "Error in Writing to STUDENT table in MySql DB: " + studentMsq.toString() 
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
