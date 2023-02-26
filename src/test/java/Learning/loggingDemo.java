package Learning;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class loggingDemo {
    @Test
    public void testLogging(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log()
                .headers(); // all, body, headers, cookies.
    }
}
