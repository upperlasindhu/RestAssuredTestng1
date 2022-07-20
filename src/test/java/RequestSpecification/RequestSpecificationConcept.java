package RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

public class RequestSpecificationConcept {
	
	@Test()
	public void request_specification()
	{
		RequestSpecification requestspecification=given().
				baseUri("https://api.postman.com").
				header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585");
		given(requestspecification).
		//given().spec(requestspecification).
			
		when().
			get("/workspaces").
		then().
			log().all().
			assertThat().statusCode(200);
			
	}
	
	
	
	
}
