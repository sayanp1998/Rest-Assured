package specBuilder;

import POJO.AddPlace;
import POJO.addPlaceLocation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        //Object create for AddPlace class
        AddPlace p = new AddPlace();
        p.getAccuracy(50);
        p.setAddress("28, Mayur Mahal, Bardhaman");
        p.setLanguagage("French-IN");
        p.getPhone_number("+91-9033421483");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline House");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        //Create add place location class object
        addPlaceLocation ap = new addPlaceLocation();
        ap.setLat(-38.383494);
        ap.setLng(33.427362);

        p.setLocation(ap); //pass the setter object value to main class method (sub JSON injected to parent JSON)

        //Create request spec builder
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        //RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Create response spec builder
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification res = given().log().all()
                .spec(req)
                .body(p);

        Response response = res.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response();

        String responseStr = response.asString();
        System.out.println(responseStr);

        //using spec builder we generalize the steps
    }
}
