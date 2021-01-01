package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBaseClass;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPatchTest extends TestBaseClass {
    //HomeWork
    @Test
    public void patchStudentEmail() {
        List<String> cources = new ArrayList<>();
        cources.add("JAVA");
        cources.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail("beta2@gmail.com");


        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .patch("/1");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
