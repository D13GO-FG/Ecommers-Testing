package Learning;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.given;

public class jsonSchemaValidation {
    //https://jsonformatter.org/json-to-jsonschema
    @Test
    public void jsonSchema(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersJsonSchema.json"));
    }
}
