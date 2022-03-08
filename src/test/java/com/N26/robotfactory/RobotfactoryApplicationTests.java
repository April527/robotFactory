package com.N26.robotfactory;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RobotfactoryApplicationTests {

	@LocalServerPort
	private int port;


	@Test
	void contextLoads() {
	}

	@Test
	public void shouldOrderARobot() throws Exception {

		postOrder("{" +
				"  \"components\": [\"I\",\"A\",\"D\",\"F\"]" + " }")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("order_id", notNullValue())
				.body("total", equalTo(160.11f));
	}

	public Response postOrder(String requestBody) throws Exception {

		return  given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.port(port)
				.post("/orders");
	}

}
