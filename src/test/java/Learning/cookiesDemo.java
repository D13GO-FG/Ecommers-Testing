package Learning;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class cookiesDemo {
    //@Test(priority = 1)
    public void testCookies(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC", "ARSKqsLDZiBJ4Z1y7cnGDsaioVEnlZ9fEJDcjNW5WEy4aGtumLmMpQ10Og")
                .log().all();
    }

    @Test(priority = 2)
    public void getCookiesInfo(){
        Response response = given()
                .when()
                .get("https://www.google.com/");
        // get single cookie info
        //String cooke_value = response.getCookie("AEC");
        //System.out.println("Value of cookie is => " + cooke_value);

        // get all cookies info
        Map<String, String> cookies_values = response.getCookies();
        //System.out.println(cookies_values.keySet());
        for (String key : cookies_values.keySet()) {
            String cookies_value = response.getCookie(key);
            System.out.println(key + " : " + cookies_value);
        }
    }
}
