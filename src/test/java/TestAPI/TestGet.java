package TestAPI;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestGet {
    ValidatableResponse validatableResponse;
    @Test
    public void getProduct(){
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1/";
        validatableResponse = given().
                when().
                get("products/259").
                then().
                assertThat().
                statusCode(200);
        System.out.println("Response: " + validatableResponse.extract().asPrettyString());
    }
}
