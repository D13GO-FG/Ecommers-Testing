package Learning;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class headersDemo {
    //@Test(priority = 1)
    public void testHeaders(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .log().headers();
    }

    @Test(priority = 2)
    public void getHeaders(){
        Response response = given()
                .when()
                .get("https://www.google.com/");

        // get single Header info
        String headersValue = response.getHeader("Content-Type");
        System.out.println("The value of Content-Type header is : " + headersValue);

        // get all headers info
        Headers myHeaders = response.getHeaders();
        for (Header hd : myHeaders) {
            System.out.println(hd.getName() + " : " + hd.getValue());
        }
    }
}
