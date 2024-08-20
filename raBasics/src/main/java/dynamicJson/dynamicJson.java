package dynamicJson;

import files.payLoad;
import files.reUsable;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //given() > no suggessation visible (static)

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.Test;

public class dynamicJson {

    @Test(dataProvider="booksData")
    public void addBook(String isbn, String asile){
        RestAssured.baseURI="https://rahulshettyacademy.com";

        String response = given().log().all().header("Content-Type", "application/json")
                .body(payLoad.Addbook(isbn, asile))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        //after then() > use .log().all() to get the response body

        JsonPath js = reUsable.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

        //Delete book from store scenario > so that add book service will run next time successfully
        given().log().all().header("Content-Type", "application/json")
                .body(id)
                .delete("Library/DeleteBook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
    }

    //parameterized API test
    @DataProvider(name = "booksData")
    public Object[][] getData(){
        return new Object[][] {{"ghst", "7251"}, {"ters", "6343"}, {"wqhs", "9364"}};
        //for example - We ave 3 array's inside the the aobject > Multidimensional array
        // In the above Object 3 combinations of isbn and asile data present, so the post method will execute 3 times
        //Real time example - Banking domain > Credit Card Issue against bank account number > 100 bank account details
        // will be put in the object and executed at once
    }
}
