package com.N26.robotfactory;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RobotfactoryApplicationTests {

	@LocalServerPort
	private int port;

	private static String requestBody = "{" +
			"  \"components\": [\"I\",\"A\",\"D\",\"F\"]" + " }";


	@Test
	void contextLoads() {
	}

	@Test
	public void postOrder() throws Exception {

		Response response = given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.port(port)
				.post("/orders")
				.then()
				.extract().response();

		Assertions.assertEquals(200, response.statusCode());
	}

}
