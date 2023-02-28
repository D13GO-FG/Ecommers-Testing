package Learning.chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getUser {
    @Test
    public void testGetUser(ITestContext context){
        //int id = (int) context.getAttribute("idUser"); // This should come from createUser request
        int id = (int) context.getSuite().getAttribute("idUser");
        String bearerToken = "6e777ec86b0d0336c52519d2e9154cfbc637f74bad28f1a0bd6cc8508fe85458";

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .pathParams("id", id)
                .contentType("application/json")
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
