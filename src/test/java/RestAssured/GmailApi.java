package RestAssured;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GmailApi {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void beforeClass() {
		RequestSpecBuilder requestspecbuilder=new RequestSpecBuilder().
				setBaseUri("").setContentType(ContentType.JSON).log(LogDetail.ALL);
		requestSpecification = requestspecbuilder.build();
		
		ResponseSpecBuilder responsespecbuilder=new ResponseSpecBuilder().expectStatusCode(200).
				expectContentType(ContentType.JSON).log(LogDetail.ALL);
				responseSpecification =responsespecbuilder.build();
		
		
	}
	
	
}
