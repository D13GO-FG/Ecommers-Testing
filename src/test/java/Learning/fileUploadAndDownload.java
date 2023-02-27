package Learning;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class fileUploadAndDownload {
    String getFileToDownload;
    @Test(priority = 1)
    public void singleUpload(){
        File myFile = new File("src/test/resources/testFiles/Test_1.txt");

        Response response = given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")
                .when()
                .post("https://api.escuelajs.co/api/v1/files/upload");
//                .then()
//                .assertThat()
//                .statusCode(201)
//                .body("originalname", equalTo("Test_1.txt"))
//                .log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("originalname"), "Test_1.txt");
        System.out.println(response.body().prettyPeek());
        getFileToDownload = response.jsonPath().getString("location");
    }

    //@Test
    public void multipleUpload(){ // For "files" multiPart
        File myFile1 = new File("src/test/resources/testFiles/Test_1.txt");
        File myFile2 = new File("src/test/resources/testFiles/Test_2.txt");
        given()
                .multiPart("files", myFile1)
                .multiPart("files", myFile2)
                .contentType("multipart/form-data")
                .when()
                .post("https://api.escuelajs.co/api/v1/files/upload")
                .then()
                .assertThat()
                .statusCode(400)
                .body("[0].originalname", equalTo("Test_1.txt"))
                .body("[1].originalname", equalTo("Test_2.txt"))
                .log().all();
    }

    //@Test
    public void multipleUpload2(){ // Won't work for all kinds API
        File myFile1 = new File("src/test/resources/testFiles/Test_1.txt");
        File myFile2 = new File("src/test/resources/testFiles/Test_2.txt");

        File[] fileArr = {myFile1, myFile2};

        given()
                .multiPart("files", fileArr)
                .contentType("multipart/form-data")
                .when()
                .post("https://api.escuelajs.co/api/v1/files/upload")
                .then()
                .assertThat()
                .statusCode(500)
                .body("[0].originalname", equalTo("Test_1.txt"))
                .body("[1].originalname", equalTo("Test_2.txt"))
                .log().all();
    }

    @Test(priority = 2)
    public void downloadFile(){
        given()
                .when()
                .get(getFileToDownload)
                .then()
                .assertThat()
                .statusCode(200)
                .log().body();
    }

}
