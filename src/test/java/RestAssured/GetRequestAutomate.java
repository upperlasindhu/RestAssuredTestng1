package RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matcher;
import static org.hamcrest.MatcherAssert.assertThat;


public class GetRequestAutomate {
	
	@Test()
	public void validate_get_status_code()
	{
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
		when().
			get("/workspaces").
		then().
			log().all().
			assertThat().statusCode(200).
			body("workspaces.name",hasItems("My Workspace","Team Workspace3","workspace1","Team Workspace1","Team Workspace1","Sindhu"),
				"workspaces.type",hasItems("personal","team","team","team","team","personal"),
				"workspaces[0].name",equalTo("My Workspace"),
				"workspaces[0].name",is(equalTo("My Workspace")),
				"workspaces.size()",equalTo(16),
				"workspaces.name",hasItem("Team Workspace3"));
	}

	
	//@Test()
	public void validate_get_Response_body()
	{
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
		when().
			get("/workspaces").
		then().
			log().all().
			assertThat().statusCode(200).
			body("workspaces.name",hasItems("My Workspace","Team Workspace3","workspace1","Team Workspace1","Team Workspace1","Sindhu"),
				"workspaces.type",hasItems("personal","team","team","team","team","personal"),
				"workspaces[0].name",equalTo("My Workspace"),
				"workspaces[0].name",is(equalTo("My Workspace")),
				"workspaces.size()",equalTo(6),
				"workspaces.name",hasItem("Team Workspace3"));
	}
	
	
	//@Test()
	public void validate_get_extract_response()
	{
		//response is an interface	
		Response res=given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
		when().
			get("/workspaces").
		then().
			//log().all().
			assertThat().statusCode(200).
			extract().
			response();
		System.out.println("response= : "+res.asString());
		/* even though the response has status code andheader info.
		 * it will not print it here we need explicitly extract the status code
		and the headers and then print*/
	}
	
	
	
	//@Test()
	public void validate_single_value_from_response()
	{
		//response is an interface	
		String res=given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
		when().
			get("/workspaces").
		then().
			//log().all().
			assertThat().statusCode(200).
			extract().
			response().path("workspaces[0].name");
			System.out.println("workspace name is = : "+res);
		
		
		
			/*response().asString();
		    System.out.println("workspace is =: "+JsonPath.from(res).getString("workspaces[0].name"));*/
		
		JsonPath jsonpath=new JsonPath(res.toString());//asstring
		System.out.println("workspace name is = : "+jsonpath.getString("workspaces[0].name"));
		
		//System.out.println("workspace name is = : "+res.path("workspaces[0].name"));
		
	}		
	
	
	//@Test()
	public void hancrest_assert_on_extracted_response()
	{
		
		
		//response is an interface	
				String res=given().
					baseUri("https://api.postman.com").
					header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
				when().
					get("/workspaces").
				then().
					//log().all().
					assertThat().statusCode(200).
					extract().
					response().path("workspaces[0].name");
					System.out.println("workspace name is = : "+res);
					
					//assertThat(res,equalTo("My Workspace"));//hamcrest assert
					Assert.assertEquals(res,"My Workspace");//by using testng
				
			
					/*response().asString();
				    System.out.println("workspace is =: "+JsonPath.from(res).getString("workspaces[0].name"));*/
				
				//System.out.println("workspace name is = : "+JsonPath.getString("workspaces[0].name"));
				
				//System.out.println("workspace name is = : "+res.path("workspaces[0].name"));
			

	}
	

	
	//@Test()
	public void request_and_response_logging()
	{
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
			log().all().
		when().
			get("/workspaces").
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	
	
	//@Test()
	public void logging_iferror()
	{
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
			log().all().
		when().
			get("/workspaces").
		then().
			log().ifError().
			assertThat().statusCode(200);
	}
	
	
	//@Test()
	public void log_only_if_validation_fails()
	{
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
			config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
			//log().ifValidationFails().
		when().
			get("/workspaces").
		then().
			//log().ifValidationFails().
			assertThat().statusCode(200);
	}
	
	//@Test()
	public void black_list_headers()
	{
		Set<String> headers=new HashSet<String>();
		headers.add("X-Api-key");
		headers.add("Accept");
		
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
			config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
			log().all().
		when().
			get("/workspaces").
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	
	
	
	//@Test()
	public void multiple_header_request()
	{
		Header header=new Header("header","value1");
		Header matchheader=new Header("x-mock-match-request-headers","header");
		given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			header(header).
			header(matchheader).
			
			
			
			header("header","value1").
			header("header","value2").
			header("x-mock-match-request-headers","header").
			log().all().
		when().
			get().
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	
	
	//@Test()
	public void multiple_headers_request()
	{
		Header header=new Header("header","value1");
		Header matchheader=new Header("x-mock-match-request-headers","header");
		
		Headers headers=new Headers(header,matchheader);
		given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			headers(headers).
			
			when().
			get().
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	
	
	
	//@Test()
	public void multiple_headers_request_using_map()
	{
		HashMap<String,String> hash=new HashMap<String,String>();
 		hash.put("header","value2");
 		hash.put("x-mock-match-request-headers","header");
		
		
		given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			headers(hash).
			
			when().
			get().
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	//@Test()
	public void multiple_value_headers_request_using()
	{
//		HashMap<String,String> hash=new HashMap<String,String>();
// 		hash.put("header","value2");
// 		hash.put("x-mock-match-request-headers","header");
//		
		
		Header header=new Header("multiValueHeader","value1");
		Header header1=new Header("multiValueHeader","value2");
		
		Headers headers=new Headers(header,header1);
		
		
		
		given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			//headers(hash).
			//header("multiValueHeader","value1","value2").
			
			headers(headers).
			log().headers().
			
		when().
			get().
		then().
			log().all().
			assertThat().statusCode(200);
	}
	
	
	//@Test()
	public void multiple_headers_request_Hashmap()
	{
		HashMap<String,String> hash=new HashMap<String,String>();
 		hash.put("header","value1");
 		hash.put("x-mock-match-request-headers","header");
		
		
		given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			headers(hash).
			
			when().
			get().
		then().
			log().all().
			assertThat().statusCode(200).
			headers("responseheader","resvalue1","X-RateLimit-Limit","120");
//			header("responseheader","resvalue1").
//			header(,"X-RateLimit-Limit","120");
	}
	
	
	
	//@Test()
	public void extract_response_headers()
	{
		HashMap<String,String> hash=new HashMap<String,String>();
 		hash.put("header","value1");
 		hash.put("x-mock-match-request-headers","header");
		
		
		Headers extractedheaders=given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			headers(hash).
			
			when().
			get().
		then().
			log().all().
			assertThat().statusCode(200).
			extract().headers();
		
		for(Header header: extractedheaders)
		{
			System.out.print("header name is ="+header.getName()+"   ,   ");
			System.out.println("header value is = "+header.getValue());
		}
		System.out.println("header name "+extractedheaders.get("responseheader").getName());
		System.out.println("header value "+extractedheaders.get("responseheader").getValue());
		System.out.println("header value "+extractedheaders.getValue("responseheader"));
	
	}
	
	
	//@Test
	
	public void extract_multi_value_headers()
	{
		HashMap<String,String> hash=new HashMap<String,String>();
 		hash.put("header","value1");
 		hash.put("x-mock-match-request-headers","header");
		
		
		Headers extractedheaders=given().
			baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
			
			headers(hash).
			
			when().
			get().
		then().
			log().all().
			assertThat().statusCode(200).
			extract().headers();
		List<String> values=extractedheaders.getValues("multiValueHeader");
		for(String value:values)
		{
			System.out.println(values);
		}
	
}
}