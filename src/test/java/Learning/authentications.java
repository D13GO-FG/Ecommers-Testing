package Learning;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class authentications {
    //@Test(priority = 1)
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

    //@Test(priority = 2)
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

    //@Test(priority = 3)
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

    //@Test
    public void testOAuth1Authentication(){
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecrete") //This is for OAuth1.0
                .when()
                .get("url")
                .then()
                .log().all();
    }

    //@Test
    public void testOAuth2Authentication(){
        given()
                .auth().oauth2("create token in github")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testAPIKeyAuthentication(){
        // Method 1
//        given()
//                .queryParam("appid","token from provider key") // appid is API Key
//                .when()
//                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
//                .then()
//                .statusCode(200)
//                .log().all();

        // Method 2
        given()
                .queryParam("appid", "API Token")
                .pathParams("mypath", "data/2.5/forecast/daily")
                .queryParam("q", "Delhi")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")
                .when()
                .get("https://api.openweathermap.org/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
