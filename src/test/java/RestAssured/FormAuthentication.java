package RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.RestAssured;

public class FormAuthentication {

	
	@BeforeClass
	public void beforeclass()
	{
			RestAssured.requestSpecification=new RequestSpecBuilder().
					setRelaxedHTTPSValidation().
					setBaseUri("https://localhost:8081").build();
			
	}
	
	@Test
	public void from_authntication_using_csrf_token()
	{
		given().
				auth().form("dan", "dan123", new FormAuthConfig("/signin","txtUsername","txtPassword")).
				log().all().
		when().
			get("/login").
		then().
		log().all().
		assertThat().
			statusCode(200);
	}
}
