package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.entity.CustomerCsv;
import personal.learning.app.entity.CustomerDb;

public class SkipBadRecordListenerForCustomer {
	
	@OnSkipInRead
    public void skipInRead(Throwable th) {
		String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_Dump_Huge_Data_From_CSV_To_DB_Concurrently_N42\\"
				  + "Chunk Job\\SimpleStep\\Reader\\"
				  + "SkipedInReaderBadRecord.txt";

		String errorMsg = (th instanceof FlatFileParseException) 
		  ? ((FlatFileParseException) th).getInput() 
		  : th.getMessage();
		createFile(filePath, "Error in reading row from CSV file: " + errorMsg);
    }

    @OnSkipInProcess
    public void skipInProcessor(CustomerCsv customerCsv, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_Dump_Huge_Data_From_CSV_To_DB_Concurrently_N42\\"
				  + "Chunk Job\\SimpleStep\\Processor\\"
				  + "SkipedInProcessorBadRecord.txt";

		createFile(filePath, "Error in processing row from CSV file:  " 
								+ customerCsv.toString() + "\nException: " + th.getMessage());
    }

    @OnSkipInWrite
    public void skipInWriter(CustomerDb customerDb, Throwable th) {
    	String filePath = "C:\\Users\\DELL\\Desktop\\Batch_Processing_With_Spring_Batch_And_Spring_Boot\\"
				  + "Spring_Batch_Dump_Huge_Data_From_CSV_To_DB_Concurrently_N42\\"
				  + "Chunk Job\\SimpleStep\\Writer\\"
				  + "SkipedInWriterBadRecord.txt";
  	
        createFile(filePath, "Error in Writing to COURSE table in MySql DB: " + customerDb.toString() 
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
