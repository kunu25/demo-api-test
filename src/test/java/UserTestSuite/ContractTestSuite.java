package UserTestSuite;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import UserDataProviders.CreateUserProvider;
import UserDataProviders.DeleteUserProvider;
import UserDataProviders.ModifyUserProvider;
import UserDataProviders.NotFoundUserProvider;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;

@SuppressWarnings("unchecked")
public class ContractTestSuite {

	@Test 
	void test_createData() {
		try {
			baseURI = "https://reqres.in/";

			// // Request Payload
			JSONObject request = new JSONObject();
			request.put("name", "kundan");
			request.put("job", "Engineer");

			RestAssuredMockMvc.requestSpecification = new MockMvcRequestSpecBuilder().setContentType(ContentType.JSON)
					.addHeader("content-type", "application/json").setBody(request.toJSONString()).build();

			// Setup the provider for the contract
			RestAssuredMockMvc.standaloneSetup(new CreateUserProvider());

			// Consumer Request & the mocked response.
			String responseText = 
					given()
					.when().async()
					.post("/api/users")
					.then()
					.assertThat().statusCode(200)
					.log().body().extract().asString();

			System.out.println("Mock Response from the API  " + responseText);

			// Assert that the response contains the request payload.
			Assert.assertTrue(responseText.contains(responseText));
		} finally {
			RestAssuredMockMvc.reset();
		}
	}


	@Test
	void test_deleteUserDetails() {
		try {
			baseURI = "https://reqres.in/";
			// Setup the provider for the contract
			RestAssuredMockMvc.standaloneSetup(new DeleteUserProvider());

			// Consumer Request & the mocked response.
			given()
			.when()
			.delete("/api/users/2")
			.then()
			.assertThat().statusCode(200);
		} finally {
			RestAssuredMockMvc.reset();
		}
	}

	@Test
	void test_userNotfound() {
		try {
			baseURI = "https://reqres.in/";

			// Setup the provider for the contract
			RestAssuredMockMvc.standaloneSetup(new NotFoundUserProvider());

			// Consumer Request & the mocked response.
			given()
			.when()
			.delete("/api/users/23")
			.then()
			.assertThat().statusCode(404);
		} finally {
			RestAssuredMockMvc.reset();
		}
	}

	@Test
	void test_modifyUserDetails() {
		try {
			baseURI = "https://reqres.in/";

			// // Request Payload
			JSONObject request = new JSONObject();
			request.put("job", "Software Engineer");

			RestAssuredMockMvc.requestSpecification = new MockMvcRequestSpecBuilder().setContentType(ContentType.JSON)
					.addHeader("content-type", "application/json").setBody(request.toJSONString()).build();

			// Setup the provider for the contract
			RestAssuredMockMvc.standaloneSetup(new ModifyUserProvider());

			// Consumer Request & the mocked response.
			String responseText = 
					given()
					.when()
					.async().put("/api/users/5")
					.then()
					.assertThat().statusCode(200)
					.log().body().extract().asString();

			System.out.println("Mock Response from the API  " + responseText);

			// Assert that the response contains the request payload.
			Assert.assertTrue(responseText.contains(responseText));

		} finally {
			RestAssuredMockMvc.reset();
		}
	}
}
