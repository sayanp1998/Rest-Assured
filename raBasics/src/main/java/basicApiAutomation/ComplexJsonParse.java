package basicApiAutomation;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

/*
    1. Print No of courses returned by API
    2. Print Purchase Amount
    3. Print Title of the first course
    4. Print All course titles and their respective Prices
    5. Print no of copies sold by RPA Course
    6. Verify if Sum of all Course prices matches with Purchase Amount
*/

    public static void main(String[] args) {
        JsonPath js = new JsonPath(payLoad.CoursePrice());

        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println("Number of courses: " + count);

        //Print Purchase Amount
        int purchaseAmaount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount is: " + purchaseAmaount);

        //Print Title of the first course
        //get method by default pull up the string (getString as well)
        String title1 = js.getString("courses[0].title");
        System.out.println("Title for first course is: " + title1);

        System.out.println("Print All course titles and their respective Prices--");
        //While parsing JSON alwasy traverse from parent to child
        String courseTitles = null;
        for (int i = 0; i < count; i++) {
            courseTitles = js.get("courses[" + i + "].title");
            System.out.println(courseTitles);
            System.out.println(js.get("courses["+i+"].price").toString()); //convert output value into string
        }

        System.out.println("Print no of copies sold by RPA Course--");
        for(int i=0; i<count; i++){
           String courseTtls = js.get("courses["+i+"].title");
           if(courseTtls.equalsIgnoreCase("RPA")){
               int copyCounts = js.get("courses["+i+"].copies");
               System.out.println(copyCounts);
               break;
           }
        }
    }
}
