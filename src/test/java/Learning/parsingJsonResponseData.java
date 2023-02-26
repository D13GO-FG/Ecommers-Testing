package Learning;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class parsingJsonResponseData {
    //https://reqres.in/api/users?page=2
    //@Test(priority = 1)
    public void testJsonResponse(){
        //Approach 1
        given()
                .contentType(ContentType.JSON)
                .pathParams("path", "users")
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/{path}")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("data[4].last_name", equalTo("Edwards"))
                .log().all();

        //Approach 2
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .pathParams("path", "users")
//                .queryParam("page", 2)
//                .when()
//                .get("https://reqres.in/api/{path}");
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
//        String dataLastName = response.jsonPath().get("data[4].last_name").toString();
//        Assert.assertEquals(dataLastName, "Edwards");
    }

    @Test(priority = 2)
    public void testJsonResponseBodyData(){
        //Approach 3
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParams("path", "users")
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/{path}");

        /*Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
        String dataLastName = response.jsonPath().get("data[4].last_name").toString();
        Assert.assertEquals(dataLastName, "Edwards");*/

        // JSONObject class
        JSONObject jo = new JSONObject(response.asString()); //converting response to JSON Object type
        /*
        for (int i = 0; i < jo.getJSONArray("data").length() ; i++) {
            String lastName = jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();
            System.out.println(lastName);
        }
        */

        // Search for title of the last name - validation 1
        boolean status = false;
        for (int i = 0; i < jo.getJSONArray("data").length() ; i++) {
            String lastName = jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();
            if (lastName.equals("Edwards")){
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);

        // Validation total id's - validation 2
        double totalIds = 0;
        for (int i = 0; i < jo.getJSONArray("data").length() ; i++) {
            String id = jo.getJSONArray("data").getJSONObject(i).get("id").toString();
            totalIds = totalIds + Double.parseDouble(id);
        }
        System.out.println("Total of id's : " + totalIds);
        Assert.assertEquals(totalIds, 57.0);
    }
}
