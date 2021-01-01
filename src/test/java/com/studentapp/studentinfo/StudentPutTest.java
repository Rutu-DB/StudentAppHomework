package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBaseClass;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBaseClass {


    @Test
    public void amendStudentInfo() {

        List<String> cources = new ArrayList<>();
        cources.add("JAVA");
        cources.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Alpha");
        studentPojo.setLastName("Beta");
        studentPojo.setEmail("beta@gmail.com");
        studentPojo.setProgramme("Software Testing");
        studentPojo.setCourses(cources);


        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .put("/1");
        response.then().statusCode(200);
        response.prettyPrint();
    }


}



