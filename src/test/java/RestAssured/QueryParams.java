package RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.*;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.config.EncoderConfig;
public class QueryParams {
	

	//@Test
	public void single_query()
	{
		given().
			baseUri("https://postman-echo.com").
			param("foo1","boo3").//generic
			queryParam("boo1","boo4").//specific
			log().all().
			
		when().
			get("/get").
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}

	
	//@Test
	public void multiple_query_parameters()
	{
		given().
			baseUri("https://postman-echo.com").
			
			queryParam("abc","Hi").//specific
			queryParam("abcd","Hello").
			log().all().
			
		when().
			get("/get").
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}
	
	//@Test
	public void multiple_query_parameters_HashMap()
	{
		HashMap<String,String> querparam=new HashMap<String,String>();
		querparam.put("xyz1","abc1");
		querparam.put("xyz2","abc2");
		
		given().
			baseUri("https://postman-echo.com").
			queryParams(querparam).
			log().all().
			
		when().
			get("/get").
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}
	
	
	//@Test
	public void multi_value_query_parameter()
	{
		given().
			baseUri("https://postman-echo.com").
			queryParam("root","rat1,rat2,rat3").
			//queryParam("root","rat1;rat2;rat3").//we can use ; also
			log().all().
			
		when().
			get("/get").
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}
	
	//@Test
	public void multi_value_path_parameter()
	{
		given().
			baseUri("https://reqres.in").
			pathParam("userId",2).
		
			log().all().
			
		when().
			get("/api/users/{userId}").
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}
	
	
	
	
	
	//@Test
	public void multipart_form_data()
	{
		given().
			baseUri("https://postman-echo.com").
			multiPart("foo1","bar1").
			log().all().
			
			
		when().
			post("/post").
			
			
		then().
			log().all().
			statusCode(200).
			assertThat();
		
	}
	
	

	//@Test
	public void upload_file()
	{
		String attributes="{\"name\":\"temp.txt\",\"parent\":{\"id\":\"123456\"}}";
		given().
			baseUri("https://postman-echo.com").
			multiPart("file",new File("temp.txt")).
			multiPart("attributes",attributes,"application/json").
			log().all().
			
			
		when().
			post("/post").
			
			
		then().
			log().all().
			assertThat().
			statusCode(200);
	
	}
	
	
	
	//@Test
	public void download_file() throws IOException
	{
	
		byte[] bytes=given().
			baseUri("https://raw.githubusercontent.com").
			multiPart("file",new File("temp.txt")).
			log().all().
			
			
		when().
			post("/appium/appium/master/sample-code/apps/ApiDemos-debug").
			
			
		then().
			log().all().
			assertThat().extract().response().asByteArray();
		OutputStream os=new FileOutputStream(new File("ApiDemos-debug"));
		os.write(bytes);
		os.close();
	}
	
	
	
	
	//@Test
		public void automate_form_url_encoded_request_payload()
		{
			
			given().
				baseUri("https://postman-echo.com").
				config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				formParam("key1", "value1").
				formParam("key2",  "value2").
				log().all().
				
				
			when().
				post("/post").
				
				
			then().
				log().all().
				assertThat().
				statusCode(200);
		
		}
	
		
		//@Test
				public void schema()
				{
					
					given().
						baseUri("https://postman-echo.com").
						log().all().
						
						
					when().
						get("/get").
						
						
					then().
						log().all().
						assertThat().
						statusCode(200).
						body(matchesJsonSchemaInClasspath("echofile.json"));
				
				}
	
	
	
	
}
