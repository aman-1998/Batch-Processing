package personal.learning.app.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;

import personal.learning.app.mysql.entity.InstructorMsq;
import personal.learning.app.oracle.entity.InstructorOra;

public class SkipBadRecordListener {
	
	@OnSkipInRead
    public void skipInRead(Throwable th) {
        String filePath = "C:\\Path\\To\\SkippedInReaderBadRecord.txt";
        String errorMsg = (th instanceof FlatFileParseException) 
            ? ((FlatFileParseException) th).getInput() 
            : th.getMessage();
        createFile(filePath, "Error in Read: " + errorMsg);
    }

    @OnSkipInProcess
    public void skipInProcessor(InstructorOra instructorOra, Throwable th) {
        String filePath = "C:\\Path\\To\\SkippedInProcessorBadRecord.txt";
        createFile(filePath, "Error in Process: " + th.getMessage());
    }

    @OnSkipInWrite
    public void skipInWriter(InstructorMsq instructorMsq, Throwable th) {
        String filePath = "C:\\Path\\To\\SkippedInWriterBadRecord.txt";
        createFile(filePath, "Error in Write: " + instructorMsq.toString() + "\nException: " + th.getMessage());
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
