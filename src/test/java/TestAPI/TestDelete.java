package TestAPI;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestDelete {
    ValidatableResponse validatableResponse;
    @Test
    public void deleteProduct(){
        validatableResponse = given()
                .baseUri("https://api.escuelajs.co/api/v1/")
                .contentType(ContentType.JSON)
                .when()
                .delete("products/259")
                .then()
                .assertThat()
                .statusCode(200).body(equalTo("true"));
        System.out.println("Response" + validatableResponse.extract().asPrettyString());
    }
}
