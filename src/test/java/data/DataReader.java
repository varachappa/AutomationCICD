package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File("/Users/vara/eclipse-workspace/FrameWorksSelenium/src/test/java/data/PurchaseOrder.json"));
		
//		String filepath = "/Users/vara/eclipse-workspace/FrameWorksSelenium/src/test/java/data/PurchaseOrder.json";
//		String jsonContent = FileUtils.readFileToString(Paths.get(filepath));
		ObjectMapper objmapper = new ObjectMapper();
		List<HashMap<String , String>> data = objmapper.readValue(jsonContent, new TypeReference<List<HashMap<String ,String>>>(){});
		return data ; 
		
	}
}
