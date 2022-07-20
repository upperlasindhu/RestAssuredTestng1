package RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class GetRequest {
	
	@Test
	public void getWeatherDetails()
	{
		given()
		.when()
			.get()
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.assertThat().body("city",equalTo("Hyderabad"))
		.header("Content-Type","application/json");
		
		
		
	}

}
