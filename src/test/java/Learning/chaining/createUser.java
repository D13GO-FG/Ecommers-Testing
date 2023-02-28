package Learning.chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class createUser {
    @Test
    public void testCreateUser(ITestContext context){
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "6e777ec86b0d0336c52519d2e9154cfbc637f74bad28f1a0bd6cc8508fe85458";

        int id = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
        System.out.println("Generated id is: " + id);
        //context.setAttribute("idUser", id);
        context.getSuite().setAttribute("idUser", id);
    }
}
