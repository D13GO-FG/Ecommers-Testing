package TestAPI;

import dataObjects.ItemDetails;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestPost {
    ValidatableResponse validatableResponse;
    @Test
    public void postProduct(){
        String endpoint = "https://api.escuelajs.co/api/v1/products/";
        ItemDetails pikachuDetails = new ItemDetails(
                "Pikachu pijana",
                500,
                "Pijamas Unisexo Franela Adulto Cosplay Pikachu",
                1,
                Arrays.asList("https://www.dhresource.com/albu_844327653_00/temp2.0x0.jpg")
        );
        validatableResponse = given()
                .contentType("application/json")
                .body(pikachuDetails)
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(201)
                .body("title", equalTo("Pikachu pijana"))
                .body("price", equalTo(500))
                .body("description", equalTo("Pijamas Unisexo Franela Adulto Cosplay Pikachu"))
                .body("images", hasItem("https://www.dhresource.com/albu_844327653_00/temp2.0x0.jpg"))
                .body("category.id", equalTo(1))
                .body("id", notNullValue())
                .body("creationAt", notNullValue())
                .body("updatedAt", notNullValue());
        System.out.println("Response: " + validatableResponse.extract().asPrettyString());
    }
}
