package Learning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Arrays;

//POJO --Serialize--> JSON Object --deserialize-->POJO
public class serializationDeserialization {

    //POJO --Serialize--> JSON Object
    //@Test
    public void convertPojo2Json() throws JsonProcessingException {
        //Created java object using pojo class
        Pojo_PostRequest dataPOJO = new Pojo_PostRequest();
        dataPOJO.setTitle("Stitch Pijama");
        dataPOJO.setPrice(999);
        dataPOJO.setDescription("Traje Disfraz Stitch Pijama Disney Store Talla 3");
        dataPOJO.setCategoryId(1);
        String[] imagesArr = {"https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg"};
        dataPOJO.setImages(imagesArr);

        //Convert java object ---> json object (serialization)
        ObjectMapper objectMapper =  new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataPOJO);

        System.out.println(jsonData);

    }

    //JSON --Serialize--> Object POJO
    @Test
    public void convertJson2Pojo() throws JsonProcessingException {
        //Created json data
        String jsonData = "{\r\n" +
                "  \"title\" : \"Stitch Pijama\",\r\n" +
                "  \"price\" : 999,\r\n" +
                "  \"description\" : \"Traje Disfraz Stitch Pijama Disney Store Talla 3\",\r\n" +
                "  \"categoryId\" : 1,\n" +
                "  \"images\" : [ \"https://http2.mlstatic.com/D_NQ_NP_637209-MLM31224594831_062019-O.jpg\" ]\r\n" +
                "}";
        //Convert json data ---> POJO object
        ObjectMapper objectMapper = new ObjectMapper();
        Pojo_PostRequest productPojo = objectMapper.readValue(jsonData, Pojo_PostRequest.class); //Convert json to POJO

        System.out.println("Name: " + productPojo.getTitle());
        System.out.println("Description: " + productPojo.getDescription());
        System.out.println("Price: " + productPojo.getPrice());
        System.out.println("Category: " + productPojo.getCategoryId());
        System.out.println("Images: " + Arrays.toString(productPojo.getImages()));
    }

}
