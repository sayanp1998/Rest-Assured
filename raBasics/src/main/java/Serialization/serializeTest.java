package Serialization;

import POJO.AddPlace;
import POJO.addPlaceLocation;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class serializeTest {
    public static void main(String[] args) {
        //Object create for AddPlace class
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("28, Mayur Mahal, Bardhaman");
        p.setLanguagage("French-IN");
        p.setPhone_number("+91-9033421483");
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

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response res = given().log().all()
                .queryParam("key", "qaclick123")
                .body(p) //pass the object which contain all setter values
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseStr = res.asString();
        System.out.println(responseStr);

    }
}
