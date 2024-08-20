package e2e_Ecommerce_test;

import static io.restassured.RestAssured.*;

import e2e_Ecommerce_test.POJO.LoginRequest;
import e2e_Ecommerce_test.POJO.LoginResponse;
import e2e_Ecommerce_test.POJO.OrderDetails;
import e2e_Ecommerce_test.POJO.orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITestAuthLogin {

    public static void main(String[] args) {

        //base URL spec
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        LoginRequest lr = new LoginRequest();
        lr.setUserEmail("apiauto@gmail.com");
        lr.setUserPassword("Apiauto@123");

        RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(lr);

        LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);

        String token = loginResponse.getToken(); //token
        System.out.println(token);
        String userId = loginResponse.getUserId(); //userId
        System.out.println(userId);

        //relaxedHTTPSValidation() > use to bypass SLL certifications

        //Add Product

        RequestSpecification addProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .build();
        RequestSpecification reqAddProduct = given().log().all().spec(addProduct)
                .param("productName", "laptop")
                .param("productAddedBy", "662203f7a86f8f74dcc73172")
                .param("productCategory", "Gadgets")
                .param("productSubCategory", "Electronics Gadget")
                .param("productPrice", "85000")
                .param("productDescription", "HP")
                .param("productFor", "Both")
                .multiPart("productImage", new File("C:\\Users\\sayan.pal\\Downloads\\Laptop.png"));

        //In rest assured image can be sent as attachments

        String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();

        JsonPath jp = new JsonPath(addProductResponse);
        String productId = jp.get("productId");
        String message = jp.get("message");

        //**Create Order
        RequestSpecification createOrderReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .setContentType(ContentType.JSON).build();

        //Order Deatils
        OrderDetails ordDetails = new OrderDetails();
        ordDetails.setCountry("India");
        ordDetails.setProductOrderedId(productId);

        //create order based on order details
        List<OrderDetails> ordDetailsList = new ArrayList<OrderDetails>();
        ordDetailsList.add(ordDetails);

        orders ord = new orders();
        ord.setOrders(ordDetailsList);

        RequestSpecification createOrdReq = given().log().all().spec(createOrderReq).body(ord);
       
        String responseAddOrd = createOrdReq.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();
        System.out.println(responseAddOrd);

        //View Orders
        RequestSpecification searchOrdReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("id", productId)
                .addHeader("token", token).build();

        String viewOrder = given().spec(searchOrdReq)
                .when().get("/api/ecom/order/get-orders-details")
                .then().log().all().extract().response().asString();

        JsonPath jp2 = new JsonPath(viewOrder);
        String orderId = jp2.get("orderById");
        System.out.println(orderId);

        //Delete the Product
        RequestSpecification deleteOrdReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("token", token).setContentType(ContentType.JSON).build();
        RequestSpecification deleteProdreq = given().log().all()
                .spec(deleteOrdReq).pathParam("productId", productId);
        String deleteReqMessage = deleteProdreq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();
        JsonPath jp3 = new JsonPath(deleteReqMessage);
        String delMessage = jp3.get("message");
        System.out.println(delMessage);
        Assert.assertEquals("Product Deleted Successfully", delMessage);
    }

}
