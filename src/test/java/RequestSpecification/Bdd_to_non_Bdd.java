package RequestSpecification;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.response.Response.*;
import static io.restassured.specification.RequestSpecification.*;

public class Bdd_to_non_Bdd {

	
RequestSpecification requestspecification;
	
	@BeforeClass
	public void beforeclass() {
				requestspecification=given().
				baseUri("https://api.postman.com");
		//		header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585").log().all().extract().response();

	}
	

	@Test()
	public void reuse_request_specification()
	{
		Response response=requestspecification.get("/workspaces");
	//	assertThat(response.statusCode(), equals(200));
			
	}

}
