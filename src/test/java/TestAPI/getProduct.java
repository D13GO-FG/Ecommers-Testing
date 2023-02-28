package TestAPI;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getProduct {
    ValidatableResponse validatableResponse;
    @Test
    public void testGetProduct(ITestContext context){
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1/";

        int id = (int) context.getSuite().getAttribute("id");

        validatableResponse = given()
                .pathParams("id", id)
                .contentType("application/json")
                .when()
                .get("https://api.escuelajs.co/api/v1/products/{id}")
                .then()
                .assertThat()
                .statusCode(200);
        System.out.println("Response: " + validatableResponse.extract().asPrettyString());
    }
}
