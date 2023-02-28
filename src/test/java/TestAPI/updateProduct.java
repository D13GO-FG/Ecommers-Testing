package TestAPI;

import dataObjects.ItemDetails;
import io.restassured.response.ValidatableResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class updateProduct {
    ValidatableResponse validateResponseUpdate;

    @Test
    public void testUpdateProduct(ITestContext context){
        ItemDetails data = new ItemDetails(
                "Yoda pijana",
                550,
                "Pijamas Unisexo Franela Adulto Cosplay Yoda",
                1,
                List.of("https://images.costco-static.com/ImageDelivery/imageService?profileId=12026540&itemId=100834796-847&recipeName=680")
        );

        int id = (int) context.getSuite().getAttribute("id");

        validateResponseUpdate = given()
                .contentType("application/json")
                .pathParams("id", id)
                .body(data)
                .when()
                .put("https://api.escuelajs.co/api/v1/products/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("Yoda pijana"))
                .body("price", equalTo(550));
        System.out.println("Response: " + validateResponseUpdate.extract().asPrettyString());
    }
}
