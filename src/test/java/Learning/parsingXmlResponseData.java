package Learning;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class parsingXmlResponseData {
    //@Test
    public void testXMLResponse(){
        // Approach 1
        /*
        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
         */

        // Approach 2
        Response response = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");
        String pageNumber = response.xmlPath().getString("TravelerinformationResponse.page");
        Assert.assertEquals(pageNumber,"1");
        String travelerName = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name");
        Assert.assertEquals(travelerName,"Developer");
    }

    @Test
    public void testXMLResponseBody(){
        Response response = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlObj = new XmlPath(response.asString());

        // Verify total number of travelers
        List<String> travelers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travelers.size(), 10);

        // Verify traveler name is present in response
        boolean status = false;
        List<String> travelersNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for (String travelersName : travelersNames) {
            if (travelersName.equals("Developer")){
                status = true;
                break;
            }
            System.out.println(travelersName);
        }
        Assert.assertTrue(status);
    }
}
