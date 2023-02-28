package Learning.chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class updateUser {
    @Test
    public void testUpdateUser(ITestContext context){
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "6e777ec86b0d0336c52519d2e9154cfbc637f74bad28f1a0bd6cc8508fe85458";

        //int id = (int) context.getAttribute("idUser");
        int id = (int) context.getSuite().getAttribute("idUser");

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParams("id", id)
                .body(data.toString())
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
