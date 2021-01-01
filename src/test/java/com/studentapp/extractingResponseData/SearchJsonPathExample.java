package com.studentapp.extractingResponseData;

import com.studentapp.testbase.TestBaseClass;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPathExample extends TestBaseClass {

    private static final String API_KEY = "75e3u4sgb2khg673cbv2gjup";
    static ValidatableResponse response = null;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
        response = given()
                .queryParam("query", "Samsung")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then();
    }


    // 1) Extract numItems
    @Test
    public void test001() {
        int numItem = given()
                .queryParam("query", "Samsung")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("numItems");

        //response.then().log().all().statusCode(200);
        //response.prettyPrint();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of items are: " + numItem);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract query
    @Test
    public void test002() {
        // homework
        String queryName = given()
                .queryParam("query", "Samsung")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("query");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The search query is: " + queryName);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first productName by providing list index value
    @Test
    public void test003() {
        String productName = given()
                .queryParam("query", "Samsung")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product name is: " + productName);
        System.out.println("------------------End of Test---------------------------");
    }


    // 4) Get the first list from imageEntities for the first product
    @Test
    public void test004() {
        // go to json path type item[0].imageEntities
        //Home work
      /*  String items = given()
                .queryParam("query", "Samsung")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0]");*/
        List<HashMap<String, Object>> imageEntitiy = response.extract().path("items[0].imageEntities");

        // String imageEntities = given()
        //       .queryParam("query", "Samsung")
        //     .queryParam("format", "json")
        //   .queryParam("apiKey", API_KEY)
        // .when()
        //.get("/search")
        //  .then()
        //  .extract().path("items[0]. imageEntities[0]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gift options under the first product are: " + imageEntitiy);
        System.out.println("------------------End of Test---------------------------");

    }


    // 5)Print the size of items
    @Test
    public void test005() {

        List<String> sizes = response.extract().path("items.size");

        //Home work

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the items is: " + sizes);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the Names from Items
    @Test
    public void test006() {
        List<String> names = response.extract().path("items.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are:: " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //7) Get the all the values for Name == Straight Talk SAMSUNG Galaxy A20, 32GB Black - Prepaid Smartphone
    @Test
    public void test007() {

        List<HashMap<String, Object>> values = response.extract().path("items.findAll{it.name=='Straight Talk SAMSUNG Galaxy A20, 32GB Black - Prepaid Smartphone'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for item name Straight Talk SAMSUNG Galaxy A20, 32GB Black - Prepaid Smartphone are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the sale price for Name == Straight Talk SAMSUNG Galaxy A01, 16GB Black - Prepaid Smartphone
    @Test
    public void test008() {

        //  List<HashMap<String,Object>> salesPrice = response.extract().path("items.findAll{it.name=='Straight Talk SAMSUNG Galaxy A01, 16GB Black - Prepaid Smartphone'}.salesPrice");
        List<String> salesPrice = response.extract().path("items.findAll{it.name=='Straight Talk SAMSUNG Galaxy A01, 16GB Black - Prepaid Smartphone'}.salePrice");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The sale price is " + salesPrice);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names which have salePrice less than 200
    @Test
    public void test009() {
        List<String> names = response.extract().path("items.findAll{it.salePrice<200}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of items that price is less than 200 are: " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the msrp of items that Start with name = Str
    @Test
    public void test010() {

        List<String> msrp = response.extract().path("items.findAll{it.name==~/Str.*/}.msrp");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The msrp of items that start with Str are: " + msrp);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the sale price of items that End with name = 2020
    @Test
    public void test011() {


        List<String> salePrice = response.extract().path("items.findAll{it.name==~/.*2020/}.salePrice");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The msrp of items that end with 2020 are: " + salePrice);
        System.out.println("------------------End of Test---------------------------");
    }


}
