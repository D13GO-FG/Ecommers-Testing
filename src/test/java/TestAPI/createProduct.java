package TestAPI;

import dataObjects.ItemDetails;
import io.restassured.response.ValidatableResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;

public class createProduct {
    ValidatableResponse validatableResponse;
    @Test
    public void testCreateProduct(ITestContext context){
        ItemDetails data = new ItemDetails(
                "Pikachu pijana",
                500,
                "Pijamas Unisexo Franela Adulto Cosplay Pikachu",
                1,
                List.of("https://www.dhresource.com/albu_844327653_00/temp2.0x0.jpg")
        );
        validatableResponse = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://api.escuelajs.co/api/v1/products/")
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
        int id = validatableResponse.extract().jsonPath().getInt("id");
        context.getSuite().setAttribute("id", id);
        System.out.println("Product ID is: " + id);
    }
}
