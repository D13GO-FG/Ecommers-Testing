package Learning;

/*

given()
    content type, set cookies, add auth, add param, set headers info...
when()
    get, post, put, delete
then()
    validate status code, extract response, extract headers cookies & response body...

 */

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class HTTPRequests {
    int id;
    @Test(priority = 1)
    public void getUsers(){
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(priority = 2)
    public void createUser(){
        HashMap data = new HashMap();
        data.put("name", "Diego");
        data.put("job", "Trainee");

        id = given()
                .contentType("application/json")
                .body(data)
                    .when()
                        .post("https://reqres.in/api/users")
                        .jsonPath().getInt("id");
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser(){
        HashMap data = new HashMap();
        data.put("name", "Luis Diego");
        data.put("job", "Junior");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users/" + id)
                .then()
                .assertThat()
                .statusCode(201)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .assertThat()
                .statusCode(204)
                .log().all();
    }
}
