package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	RequestSpecification req1;
	public RequestSpecification requestSpecification() throws IOException {
		
		 req1 = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON).build();
		 return req1;
	}
	
	public static String getGlobalValue(String Key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\gopik\\eclipse-workspace\\E2E_Framework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(Key);
		
	}

}

