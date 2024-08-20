package basicApiAutomation;

import files.payLoad;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestDemo {

    //@Test
    public void test1(){
        //String accessToken = jsonPath.getString("access_token");
        baseURI = "https://api.avios.com/tst/v1";
        Response res2 = given().log().all().header("bearer_token", "accessToken")
                .header("api_key", "reward-platform-key")
                .header("X-User-ID", "00040797")
                .queryParam("channel", "AGENT_TERMINAL")
                .queryParam("authenticatedSenderId", "IAGL-white-label")
                .queryParam("partner", "EI")
                .body(payLoad.CoursePrice())
                .when().delete("/basket/items") //resource or end point
                .then().assertThat().statusCode(200).extract().response();
        JsonPath jp = new JsonPath(String.valueOf(res2));
        jp.getString("");

        RequestSpecification req2 = new RequestSpecBuilder().setBaseUri("https://api.avios.com/tst/v1").addHeader("bearer_token", "accessToken")
                .addHeader("api_key", "reward-platform-key").addHeader("X-User-ID", "00040797")
                .addQueryParam("channel", "AGENT_TERMINAL").addQueryParam("authenticatedSenderId", "IAGL-white-label").addQueryParam("partner", "EI")
                .setContentType(ContentType.JSON).build();
    }

}
