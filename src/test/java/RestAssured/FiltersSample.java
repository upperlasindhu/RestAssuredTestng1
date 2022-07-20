package RestAssured;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.Test;

import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class FiltersSample {

	//@Test
	public void log_request_and_response_filters()
	{
		
		given().
			baseUri("https://postman-echo.com").
			filter(new RequestLoggingFilter(LogDetail.BODY)).
			filter(new ResponseLoggingFilter(LogDetail.STATUS)).
//			filter(new RequestLoggingFilter()).
//			filter(new ResponseLoggingFilter()).
		//	log().all().
			
			
		when().
			get("/get").
			
			
		then().
			//log().all().
			assertThat().
			statusCode(200);
	
	}
	
	
	
	@Test
	public void log_to_file() throws FileNotFoundException
	{
		PrintStream FileOutputStream=new PrintStream(new File("restAssured.log"));
		given().
			baseUri("https://postman-echo.com").
			filter(new RequestLoggingFilter(LogDetail.BODY,FileOutputStream)).
			filter(new ResponseLoggingFilter(LogDetail.STATUS,FileOutputStream)).
//			filter(new RequestLoggingFilter()).
//			filter(new ResponseLoggingFilter()).
			log().all().
			
			
		when().
			get("/get").
			
			
		then().
			log().all().
			assertThat().
			statusCode(200);
	
	}
	
}
