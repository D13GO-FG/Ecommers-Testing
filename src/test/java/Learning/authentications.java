package Learning;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class authentications {
    @Test(priority = 1)
    public void basicAuthentication(){
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    public void digestAuthentication(){
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    public void preemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 4)
    public void testBearerTokenAuthentication(){
        String bearerToken = "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";
        given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }
}
