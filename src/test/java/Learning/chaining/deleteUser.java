package Learning.chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class deleteUser {
    @Test
    public void testDeleteUser(ITestContext context){
        String bearerToken = "6e777ec86b0d0336c52519d2e9154cfbc637f74bad28f1a0bd6cc8508fe85458";

        //int id = (int) context.getAttribute("idUser");
        int id = (int) context.getSuite().getAttribute("idUser");

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .pathParams("id", id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }
}
