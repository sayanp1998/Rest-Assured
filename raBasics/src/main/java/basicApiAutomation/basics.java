package basicApiAutomation;

import com.fasterxml.jackson.core.util.RequestPayload;
import files.payLoad;
import files.reUsable;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.ITest;

//Eclipse, intelliJ will not show suggessations for static package
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basics {
    public static void main(String[] args) throws IOException {
        /*Add place->update place with new address ->
            get place to validate if new address is present in response*/

        //Validate add place api is working as expected

        //Given - All input details
        //When - Submit the api - resource, HTTP methods
        //Then - Validate the response
        //Content of the file to String > Content of the file can be convert into Byte

        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //using log().all() we log the output paramters
        /*given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();*/

        // by saving it as string we get the response as string
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\sayan.pal\\Downloads\\addPlace Request.txt"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        //pass JSON
        JsonPath jp = new JsonPath(response); //for parsing json
        String placeId = jp.getString("place_id");
            System.out.println(placeId);

        //update place
        String newAddress = "70 winter walk, USA";
        given().log().all().queryParam("key", "qaclick123").
                header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //get place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();


        JsonPath js1 = reUsable.rawToJson(getPlaceResponse); //make a class and method the make it reusable
        String actualAddress = js1.get("address");

        Assert.assertEquals(actualAddress, newAddress); //Asserting or validating the address
        System.out.println(actualAddress);

        //Test
        Response rsp1 = given().log().all().header("header", "test")
                .pathParam("42323", "w323").queryParam("test/test")
                .body("")
                .when().post("test/ post/test")
                .then().assertThat().log().all().statusCode(200).extract().response();
        //JsonPath jres = reUsable.rawToJson(String.valueOf(rsp1));
        String op = rsp1.asString();
        JsonPath jp2 = new JsonPath(op);
        jp2.getString("test");

        RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("test")
                .addHeader("header", "value")
                .addQueryParam("param")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        given().spec(reqSpec).body("PayLoad.addBook(isbn, asile)")
                .when().post("src/data")
                .then().assertThat().spec(resSpec).extract().response().asString();

    }
}
