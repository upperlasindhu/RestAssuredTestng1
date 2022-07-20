//package RequestSpecification;
//
//import static io.restassured.RestAssured.given;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//
//public class ResponseSpecification {
//
//	ResponseSpecification responsespecification;
//	
//
//	
//	@BeforeClass
//	public void beforeclass() {
////				responsespecification=given().
////				baseUri("https://api.postman.com").
////				header("X-Api-key","PMAK-62bd98581862c36385d2f104-728b75b38733a17363023cedf13fea5585");
//				ResponseSpecification responsespecification = RestAssured.expect().statusCode(200).contentType(ContentType.JSON);
//	}
//	
//
//	@Test()
//	public void validate_status_code()
//	{
//		given().spec(responsespecification).
//			
//		when().
//			get("/workspaces").
//		then().spec(responseSpecification).log().all();
//			
//	}
//	
//	
//	
//	
//}
