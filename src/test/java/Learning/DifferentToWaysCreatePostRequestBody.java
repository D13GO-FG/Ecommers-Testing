package Learning;

/*
Different ways to create POST request body
1. Post request body using Hashmap
2. Post request body creation using Org.JSON
3. Post request body creation using POJO class
4. Post request using external json file data

*/

import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class DifferentToWaysCreatePostRequestBody {
    ValidatableResponse validatableResponse;
    int id;
    // 1. Post request body using Hashmap
    //@Test(priority = 1)
    public void testPostUsingHashmap(){
        HashMap data = new HashMap();
        data.put("title", "Stitch Pijama");
        data.put("price", 999);
        data.put("description", "Traje Disfraz Stitch Pijama Disney Store Talla 3");
        data.put("categoryId", 1);

        String[] imagesArr = {"https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"};
        data.put("images", imagesArr);

        validatableResponse = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://api.escuelajs.co/api/v1/products/")
                .then()
                .assertThat().statusCode(201)
                .body("title", equalTo("Stitch Pijama"))
                .body("price", equalTo(999))
                .body("description", equalTo("Traje Disfraz Stitch Pijama Disney Store Talla 3"))
                .body("category.id", equalTo(1))
                .body("images[0]", equalTo("https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        id = validatableResponse.extract().jsonPath().get("id");
    }

    // 2. Post request body creation using Org.JSON
    //@Test(priority = 1)
    public void testPostUsingJSONLibrary(){
        JSONObject data = new JSONObject();
        data.put("title", "Stitch Pijama");
        data.put("price", 999);
        data.put("description", "Traje Disfraz Stitch Pijama Disney Store Talla 3");
        data.put("categoryId", 1);

        String[] imagesArr = {"https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"};
        data.put("images", imagesArr);

        validatableResponse = given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://api.escuelajs.co/api/v1/products/")
                .then()
                .assertThat().statusCode(201)
                .body("title", equalTo("Stitch Pijama"))
                .body("price", equalTo(999))
                .body("description", equalTo("Traje Disfraz Stitch Pijama Disney Store Talla 3"))
                .body("category.id", equalTo(1))
                .body("images[0]", equalTo("https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        id = validatableResponse.extract().jsonPath().get("id");
    }

    // 3. Post request body creation using POJO Class
    //@Test(priority = 1)
    public void testPostUsingPOJO(){
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setTitle("Stitch Pijama");
        data.setPrice(999);
        data.setDescription("Traje Disfraz Stitch Pijama Disney Store Talla 3");
        data.setCategoryId(1);

        String[] imagesArr = {"https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"};
        data.setImages(imagesArr);

        validatableResponse = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://api.escuelajs.co/api/v1/products/")
                .then()
                .assertThat().statusCode(201)
                .body("title", equalTo("Stitch Pijama"))
                .body("price", equalTo(999))
                .body("description", equalTo("Traje Disfraz Stitch Pijama Disney Store Talla 3"))
                .body("category.id", equalTo(1))
                .body("images[0]", equalTo("https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        id = validatableResponse.extract().jsonPath().get("id");
    }

    // 4. Post request using external json file data
    @Test(priority = 1)
    public void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File file = new File("src/test/resources/JsonFiles/body.json");
        FileReader fileReader = new FileReader(file);

        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject data = new JSONObject(jsonTokener);

        validatableResponse = given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://api.escuelajs.co/api/v1/products/")
                .then()
                .assertThat().statusCode(201)
                .body("title", equalTo("Stitch Pijama"))
                .body("price", equalTo(999))
                .body("description", equalTo("Traje Disfraz Stitch Pijama Disney Store Talla 3"))
                .body("category.id", equalTo(1))
                .body("images[0]", equalTo("https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        id = validatableResponse.extract().jsonPath().get("id");
    }

    @Test(priority = 2)
    public void testDelete(){
        given()
                .when()
                .delete("https://api.escuelajs.co/api/v1/products/" + id)
                .then().assertThat().statusCode(200);
    }
}
