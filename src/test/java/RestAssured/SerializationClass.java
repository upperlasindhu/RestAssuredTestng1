package RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;
public class SerializationClass {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	//@BeforeClass
    public void bc() {
        RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("x-api-key", "PMAK-62cea93b3e26b36e1644f8a2-5993f4deaba7cef3a33ccf152471b31869")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=reqspecbuilder.build();

        ResponseSpecBuilder respspecbuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification=respspecbuilder.build();
    }
	
	//@Test
	public void validate_post_request_payload_as_map() throws JsonProcessingException {
		HashMap<String,Object> mainobject=new HashMap<String,Object>();
		
		HashMap<String,Object> nestedobject=new HashMap<String,Object>();
		nestedobject.put("name", "workspace@");
		nestedobject.put("type", "decsription");
		nestedobject.put("description", "Hi this is restassured");
		
		mainobject.put("workspace", nestedobject);
		
		ObjectMapper objectmapper=new ObjectMapper();
		String mainobjectstr=objectmapper.writeValueAsString(mainobject);
		
		given().
			body(mainobject).
		when().
			post("/workspaces").
		then().spec(responseSpecification).assertThat().body("workspace.name",equalTo("workspace@"));
		
		
		
	}




	@Test
	public void serialize_list_to_JSON() throws JsonProcessingException {
		HashMap<String,String> obj5001=new HashMap<String,String>();
		obj5001.put("id", "5001");
		obj5001.put("type", "None");
		
		
		
		HashMap<String, String> obj5002=new HashMap<String,String>();
		obj5002.put("id", "5002");
		obj5002.put("type", "Glazed");
		
		List<HashMap<String,String>> jsonlist=new ArrayList<HashMap<String,String>>();
		jsonlist.add(obj5001);
		jsonlist.add(obj5002);
		
		
		

		
		ObjectMapper objectmapper=new ObjectMapper();
		String jsonListstr=objectmapper.writeValueAsString(jsonlist);
		
	
		given().
			body(jsonListstr).
		when().
			post("/workspaces").
		then().
		spec(responseSpecification).assertThat().body("msg",equalTo("Success"));
		
		
		
	}


}


