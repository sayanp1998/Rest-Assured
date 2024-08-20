package oAuth;

import POJO.API;
import POJO.GetCourse;
import POJO.webAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class oAuthTest {
    public static void main(String[] args) {

        String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();


        System.out.println(response);
        //perse into json using json path
        JsonPath jsonPath = new JsonPath(response);
        //using object of JsonPath class we can fetch the parameters of the response
        String accessToken = jsonPath.getString("access_token");


        /*//get course details call
        String courseResponse = given().queryParam("access_token", accessToken)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .asString();
        System.out.println(courseResponse);*/

        GetCourse gc = given()
                .queryParam("access_token", accessToken)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourse.class);
        System.out.println(gc.getLinkedIn()); //get the value of linkedIN key
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle()); //get the course title at index 1

        //print the price of a course given in teh if condition
        List<API> apiCourses = gc.getCourses().getApi();
        for(int i=0; i<apiCourses.size(); i++){ //Iterating api courses list array
            if(apiCourses.get(i).getCourseTitle()
                    .equalsIgnoreCase("Rest Assured Automation using Java")){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        // print all web automation courses title
        List<webAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
        for(int i=0; i<webAutomationCourses.size(); i++){
            System.out.println(webAutomationCourses.get(i).getCourseTitle());
        }

        //Get the web automation course name Dynamically
        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};//Expected course titles
        ArrayList<String> a = new ArrayList<String>();

        List<webAutomation> webAutomationCoursesList = gc.getCourses().getWebAutomation();
        for(int i=0; i<webAutomationCourses.size(); i++){
            a.add(webAutomationCourses.get(i).getCourseTitle()); //Acutal Course titles
        }
        List<String> expectedList = Arrays.asList(courseTitles); //Convert Array to ArrayList
        Assert.assertTrue(a.equals(expectedList)); //Asserting > matching expected with actual
    }
}
