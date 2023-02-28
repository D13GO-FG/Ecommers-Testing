package TestAPI;

import io.restassured.response.ValidatableResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class deleteProduct {
    ValidatableResponse validatableResponse;
    @Test
    public void testDeleteProduct(ITestContext context){
        int id = (int) context.getSuite().getAttribute("id");

        validatableResponse = given()
                .contentType("application/json")
                .pathParams("id", id)
                .when()
                .delete("https://api.escuelajs.co/api/v1/products/{id}")
                .then()
                .assertThat()
                .statusCode(200).body(equalTo("true"));
        System.out.println("Response" + validatableResponse.extract().asPrettyString());
    }
}
