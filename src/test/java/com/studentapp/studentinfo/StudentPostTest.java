package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBaseClass;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBaseClass {

    @Test
    public void createStudent(){
        List<String> cources = new ArrayList<>();
        cources.add("JAVA");
        cources.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Alpha");
        studentPojo.setLastName("Beta");
        studentPojo.setEmail("alphatesting1@gmail.com");
        studentPojo.setProgramme("Software Testing");
        studentPojo.setCourses(cources);

       Response response = given()
               .header("Content-Type","application/json")
               .when()
               .body(studentPojo)
               .post();
       response.then().statusCode(201);
       response.prettyPrint();
    }



}
