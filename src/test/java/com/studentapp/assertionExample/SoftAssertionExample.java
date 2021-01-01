package com.studentapp.assertionExample;

import com.studentapp.testbase.TestBaseClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SoftAssertionExample extends TestBaseClass {


    @Test
    public void hardAssert() {
        given().when().get("/list").then().log().all()
                .body("[0].id", equalTo(1))
                .body("[0].firstName", equalTo("Vernon"))
                .body("[0].lastName", equalTo("Harper"))
                .body("[0].programme", equalTo("Financial Analysis"));
    }

    @Test
    public void softAssert() {
        given().when().get("/list").then().log().all()
                .body("[0].id", equalTo(1), "[0].firstName", equalTo("Vernon1"),
                        "[0].lastName", equalTo("Harper"),
                        "[0].programme", equalTo("Financial Analysis1"));

    }

}
