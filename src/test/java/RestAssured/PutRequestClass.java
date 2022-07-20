package RestAssured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;

import io.restassured.specification.RequestSpecification;

public class PutRequestClass {

RequestSpecification requestspecification;
	
	@BeforeClass
	public void beforeclass() {
				requestspecification=given().
				baseUri("https://api.postman.com").
				header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585");

	}
	
}
