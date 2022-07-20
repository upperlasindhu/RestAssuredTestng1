package RestAssured;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
public class PostRequest {
	
	
	
RequestSpecification requestspecification;
	
	//@BeforeClass
	public void beforeclass() {
				requestspecification=given().
				baseUri("https://api.postman.com").
				header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585");

	}
	
	//@Test
	public void post_request()
	{
		String payload ="{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"Team Workspacesindhu\",\r\n"
				+ "        \"type\": \"team\",\r\n"
				+ "        \"description\": \"This is where the collaboration happens. Use this space to share and collaborate on APIs, collections, environments, monitors, and mocks.\"\r\n"
				+ "    }\r\n"
				+ "}";
		given().
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
			body(payload).
		when().
			post("/workspaces").
		then().
		    log().all().
			assertThat().
			body("workspace.name",equalTo("Team Workspacesindhu"));
				//"workspace.id",matchesPattern(""));
		
	}
	
	
	
	
	
	
	
	//@Test
	public void post_request_payload_file() throws FileNotFoundException {
//		BufferedReader br = new BufferedReader(new FileReader(new 
//				File("./CreateWorkspacePayload.Json")));
		File file=new File("/RestAssured1/src/main/resources/file/CreateWorkspacePayload.Json");
//		HashMap<String, Object> mainobject=new HashMap<String, Object>();
//		HashMap<String, String> nestedobject=new HashMap<String, String>();
//		nestedobject.put("name", "My ThirdWorkspace");
//		nestedobject.put("type","team");
//		
//		
//		mainobject.put("workspace", nestedobject);
		
		given().
			body(file).
			baseUri("https://api.postman.com").
			header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").
		when().
			post("workspaces/").
		then().
			log().all().
			assertThat().
			body("workspace.name",equalTo("My ThirdWorkspace"));
		
	
	}
	
	//@Test
	public void validate_post_request_payload_json_array_as_list()
	{
		HashMap<String,String> obj5001=new HashMap<String,String>();
		obj5001.put("id", "5001");
		obj5001.put("type", "None");
		
		HashMap<String,String> obj5002=new HashMap<String,String>();
		obj5002.put("id", "5002");
		obj5002.put("type", "Glazed");

		List<HashMap<String,String>> jsonlist=new ArrayList<HashMap<String,String>>();
		jsonlist.add(obj5001);
		jsonlist.add(obj5002);
		
		
		given().
		    baseUri("https://e5d69355-e13f-4db4-80c0-675fa71abb37.mock.pstmn.io").
		    headers("x-mock-match-request-body","true").
		  //  config(config.encoderConfig(EncoderConfig.encoderConfig().
		  //  		appendDefaultContentCharsetToContentTypeIfUndefined(false)))
		    		contentType("application/x-www-form-urlencoded").
			body(jsonlist).
		when().
			post("/post").
		then().
			log().all();
			//assertThat().body("msg",equalTo("Success"));

		
	
	}
	
	
	
	
	public void validate_post_request_payload_complex_json()
	{
	List<Integer> idArrayList=new ArrayList<Integer>();
	idArrayList.add(5);
	idArrayList.add(9);
	
	HashMap<String,Object> batterhashmap2=new HashMap<String,Object>();
	batterhashmap2.put("id", idArrayList);
	batterhashmap2.put("type","Chocolate");
	
	
	HashMap<String,Object> batterhashmap1=new HashMap<String,Object>();
	batterhashmap1.put("id", idArrayList);
	batterhashmap1.put("type","regular");
	
	given().
		body("").
	when().
		post().
	then();
	
	
	}
}




