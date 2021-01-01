package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBaseClass;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest  extends TestBaseClass {
    //HomeWork
@Test
            public void deleteRecord() {
    Response response = given()
            .pathParam("id", 10) // it is from the {{1}} in post man
            .when()
            .delete("/{id}");
    response.then().statusCode(204);

    response.prettyPrint();
}
}




