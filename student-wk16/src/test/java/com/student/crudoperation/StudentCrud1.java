package com.student.crudoperation;

import com.student.model.StudentPojo;
import com.student.testbase.TestBase;
import com.student.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StudentCrud1 extends TestBase {
    static String firstName = "Tanuja";
    static String lastname = "Patel";
    static String programme = "Application Programme";
    static String email = TestUtils.getRandomValue()+ "@gmail.com";
    static int studentId;

    //Get all student list
    @Test(priority = 1)
    public void getAllStudentInfo(){
        given().log().all()
                .when()
                .get("/list")
                .then().log().all()
                .statusCode(200);
    }

    //Get student by id
    @Test(priority = 2)
    public void getStudentInfoById(){
        Response response =given().log().all()
                .pathParam("id","1")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
    }

    //Create New Student
    @Test(priority = 3)
    public void createStudentData(){
        List<String>courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("selenium");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastname);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response =given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(studentPojo)
                .post();
        response.then().statusCode(201);
    }

    //extract student data by id
    @Test(priority = 4)
    public void extractStudentData(){

        HashMap<String, Object> studentData =given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract().path("findAll{it.firstName =='"+firstName+"'}.get(0)");

        studentId= (int) studentData.get("id");
        System.out.println(studentId);
    }

    //verify dats has been created
    @Test(priority = 5)
    public void getStudentById(){
        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200).body("firstName", equalTo(firstName));
    }

    //update Student data
    @Test(priority = 6)
    public void updateStudentById(){
        List<String>courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("selenium");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Tanya");
        studentPojo.setLastName("Gray");
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response =given().log().all()
                .header("Content-Type","application/json")
                .pathParam("id",studentId)
                .when()
                .body(studentPojo)
                .put("/{id}");
        response.then().log().all().statusCode(200);
    }

    //delete
    @Test(priority = 7)
    public void deleteStudentById(){
        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .delete("/{id}");
        response.then().log().all().statusCode(204);
    }

    //verify Student data has been deleted
    @Test(priority = 8)
    public void verifyStudentDeletedById(){
        Response response =given().log().all()
                .pathParam("id",studentId)
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(404);
    }
}
