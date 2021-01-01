package com.studentapp.assertionExample;

import com.studentapp.testbase.TestBaseClass;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssertionExampleDemo extends TestBaseClass {

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

    // 1) Verify if the number of items is equal to 10
    @Test
    public void test001() {
   /* int numItems = response.extract().path("numItems");
        Assert.assertEquals(10, numItems);
*/
        response.body("numItems", equalTo(10));
    }

    // 2) Verify Query = Samsung
    @Test
    public void test002() {
        response.body("query",containsString("Samsung"));
    }

    // 3) Check Single Name in ArrayList (SAMSUNG 55\" Class 4K Crystal UHD (2160P) LED Smart TV with HDR UN55TU7000 2020)
    @Test
    public void test003() {
        response.body("items.name", hasItem("SAMSUNG Chromebook 4 11.6\" Intel Celeron Processor N4000 4GB RAM 32GB eMMC Intel UHD Graphics 600 - XE310XBA-K01US (Google Classroom Ready)"));

    }

    // 4) Check Multiple Names in ArrayList (SAMSUNG Chromebook 4 11.6\" Intel Celeron Processor N4000 4GB RAM 32GB eMMC Intel UHD Graphics 600 - XE310XBA-K01US (Google Classroom Ready), Straight Talk SAMSUNG Galaxy A01, 16GB Black - Prepaid Smartphone)
    @Test
    public void test004() {
        //homework

        response.body("items.name", hasItems("SAMSUNG Chromebook 4 11.6 Intel Celeron Processor N4000 4GB RAM 32GB eMMC Intel UHD Graphics 600 - XE310XBA-K01US (Google Classroom Ready), Straight Talk SAMSUNG Galaxy A01, 16GB Black - Prepaid Smartphone"));

    }

    // 5) Verify the entityType inside imageEntities for the first product (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {
        response.body("items[0].imageEntities[0]", hasKey("entityType"));

    }

    // 6) Check hash map values inside ""name": "SAMSUNG 55\" Class 4K Crystal UHD (2160P) LED Smart TV with HDR UN55TU7000 2020", "a list categoryPath = Home Page/Electronics/TV & Video/All TVs
    @Test
    public void test006() {
    response.body(       "items.findAll{it.categoryPath=='Home Page/Electronics/TV & Video/All TVs'}",
            hasItems(hasEntry("name","SAMSUNG 55\" Class 4K Crystal UHD (2160P) LED Smart TV with HDR UN55TU7000 2020")));
    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {
        response.body("numItems",equalTo(10))
                .body("items[0].imageEntities[0]", hasKey("entityType"));
               /* .body("items.findAll{it.categoryPath=='Home Page/Electronics/TV & Video/All TVs'}\",\n" +
                        "            hasItems(hasEntry(\"categoryPath\",\"SAMSUNG 55\\\" Class 4K Crystal UHD (2160P) LED Smart TV with HDR UN55TU7000 2020");
*/

        response.body("numItems", equalTo(10))
                .body("items[0].imageEntities[0]", hasKey("entityType"))
                .body("items.name", hasItem("SAMSUNG Chromebook 4 11.6\" Intel Celeron Processor N4000 4GB RAM 32GB eMMC Intel UHD Graphics 600 - XE310XBA-K01US (Google Classroom Ready)"));


    }

    // 8) Logical Assertions
    @Test
    public void test008() {

        response.body("items.size()", equalTo(10))
                .body("items.size()",lessThan(12))
                .body("items.size()",greaterThan(8))
                .body("items.size()", lessThanOrEqualTo(10));

    }


}
