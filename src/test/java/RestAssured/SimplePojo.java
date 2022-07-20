package RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.pojo.SamplePojo1;

import io.restassured.RestAssured;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SimplePojo {
	ResponseSpecification responseSpecification; 
	
	@BeforeClass
	public void beforeClass() {
		RequestSpecBuilder  requestspecbuilder=new RequestSpecBuilder().setBaseUri("https://ec20c7b8-fb67-4a67-9782-263b1423f68f.mock.pstmn.io").
				setContentType(ContentType.JSON).log(LogDetail.ALL);
		
		RestAssured.requestSpecification =requestspecbuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON) .log(LogDetail.ALL);
		
				responseSpecification=responseSpecBuilder.build();
	}
	
	@Test
	public void simple_pojo_serialized() {
		
		//SamplePojo1 simplepojo=new SamplePojo1("value1","value2");
		
		SamplePojo1 simplepojo=new SamplePojo1();
		simplepojo.setKey1("value1");
		simplepojo.setKey2("value2");
		
		
		
		String payload="{\r\n"
				+ "    \r\n"
				+ "        \"key1\": \"value1\",\r\n"
				+ "        \"key2\": \"value2\"\r\n"
				+ "    \r\n"
				+ "}";
		
		given().
				body(payload).
		when().
			post("/post").
		then().spec(responseSpecification).
		
			//assertThat().body("key1",equalTo("value1"),"key2",equalTo("value2"));
		assertThat().body("key1",equalTo(simplepojo.getKey1()),"key2",equalTo(simplepojo.getKey2()));

	}

	
	//@Test
	public void simple_pojo_deserialized() throws JsonProcessingException {
		
		SamplePojo1 simplepojo=new SamplePojo1("value1","value2");
		
		/* SamplePojo1 simplepojo=new SamplePojo1();
		simplepojo.setKey1("value1");
		simplepojo.setKey2("value2");*/
		
		
		
		SamplePojo1 deserializedPojo=given().
				body(simplepojo).
	
		when().
			post("/post").
		then().spec(responseSpecification).
		extract().response().as(SamplePojo1.class);
		
		ObjectMapper objectMapper=new ObjectMapper();
		String deserializedPojoStr=objectMapper.writeValueAsString(deserializedPojo);
		String simplepojostr=objectMapper.writeValueAsString(simplepojo);
		assertThat(objectMapper.readTree(deserializedPojoStr), equalTo(objectMapper.readTree(simplepojostr)));

	}

	
}
