package personal.learning.app.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class CsvRowPartitioner implements Partitioner {

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		int min = 1;
		int max = 1000;
		
		int targetSize = (max - min) / gridSize + 1;
		System.out.println("Target size : " + targetSize);
		
		Map<String, ExecutionContext> result = new HashMap<>();
		
		int number = 0;
		int start = min;
		int end = start + targetSize - 1;
		
		while(start <= max) {
			
			ExecutionContext executionContext = new ExecutionContext();
			if(end >= max) {
				end = max;
			}
			executionContext.putInt("minValue", start);
			executionContext.putInt("maxValue", end);
			start = start + targetSize;
			end = end + targetSize;
			result.put("partition" + number, executionContext);
		}
		
		System.out.println("Partition result : " + result.toString());
		return result;
	}

}
