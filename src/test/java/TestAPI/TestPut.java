package TestAPI;

import dataObjects.ItemDetails;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class TestPut {
    ValidatableResponse validatableResponse;
    ValidatableResponse validatableResponseUpdate;

    @Test
    public void updateProduct(){
        validatableResponse = given()
                .baseUri("https://api.escuelajs.co/api/v1/")
                .contentType(ContentType.JSON)
                .when()
                .get("products/259")
                .then()
                .assertThat()
                .statusCode(200);
        System.out.println("Response: " + validatableResponse.extract().asPrettyString());

        ItemDetails yodaDetails = new ItemDetails(
                "Yoda pijana",
                550,
                "Pijamas Unisexo Franela Adulto Cosplay Yoda",
                1,
                Arrays.asList("https://images.costco-static.com/ImageDelivery/imageService?profileId=12026540&itemId=100834796-847&recipeName=680")
        );

        validatableResponseUpdate = given()
                .baseUri("https://api.escuelajs.co/api/v1/")
                .contentType(ContentType.JSON)
                .body(yodaDetails).when()
                .put("products/259")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("Yoda pijana"))
                .body("price", equalTo(550));
        System.out.println("Response: " + validatableResponseUpdate.extract().asPrettyString());
    }
}
