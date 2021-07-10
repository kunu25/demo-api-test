package UserTestSuite;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class UserDataTestSuite {


	@SuppressWarnings("unchecked")
	@Test
	void test_createData(){

		baseURI = "https://reqres.in/";
		
		JSONObject request = new JSONObject();
		request.put("name", "kundan");
		request.put("job", "Engineer");


		given().
			header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("/api/users")
		.then()
			.statusCode(201);
	}

	@SuppressWarnings("unchecked")
	@Test
	void test_modifyUserDetails(){
		baseURI = "https://reqres.in/";
		JSONObject request = new JSONObject();
		request.put("job", "IT Engineer");

		given().
			header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("/api/users/5")
		.then()
			.statusCode(200);
	}

	@Test
	void test_deleteUserDetails(){
		baseURI = "https://reqres.in/";

		given()
		.when()
			.delete("/api/users/2")
		.then()
			.statusCode(204);
	}

	
	@Test
	void test_userNotfound(){
		baseURI = "https://reqres.in/";
		
		given()
		.when()
			.get("/api/users/23")
		.then()
			.statusCode(404);
	}
	
}
