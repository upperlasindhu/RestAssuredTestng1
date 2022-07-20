package RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class StaticImports {

	@Test()
	public void test()
	{
		given().
			baseUri("https://api.postman.com").header("x-api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585")
		.when()
		.get("/workspaces").
		
		then().
			statusCode(200).
			log().all().
			body("name",is(equalTo("Team Workspace1")),
			"type",is(equalTo("team")));
			
		
	}


}
