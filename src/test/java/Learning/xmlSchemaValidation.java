package Learning;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class xmlSchemaValidation {
    //https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
    @Test
    public void xmlSchemaValid(){
        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .assertThat()
                .body(RestAssuredMatchers.matchesXsdInClasspath("travelerXsdSchema.xsd"));
    }
}
