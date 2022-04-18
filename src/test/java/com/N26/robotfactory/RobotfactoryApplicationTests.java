package com.N26.robotfactory;

import com.N26.robotfactory.domain.usecase.RobotUseCase;
import com.N26.robotfactory.gateway.IStock;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RobotUseCase robotUseCase;

	@Autowired
	IStock stockRepository;

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

	@Test
	public void shouldNotAllowInvalidBody() throws Exception {

		postOrder("{" +
				"  \"components\": \"BENDER\"" + " }")
				.then()
				.assertThat()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	//TODO: Modify the code so it returns a 422
	@Test
	public void shouldNotAllowInvalidRobotConfiguration() throws Exception {

		postOrder("{" +
				"  \"components\": [\"A\",\"C\",\"I\",\"D\"]" + " }")
				.then()
				.assertThat()
				.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
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
