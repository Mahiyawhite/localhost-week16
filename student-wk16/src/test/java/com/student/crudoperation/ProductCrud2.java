package com.student.crudoperation;

import com.student.model.Datum;
import com.student.model.Products;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductCrud2 {

    int idNumber;
    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
    @Test(priority = 1)
    public void getAllProduct(){
        Products products = given()
                .when()
                .get()
                .getBody().as(Products.class);
        System.out.println(products.getTotal());
    }

    @Test(priority = 2)
    public void createNewProduct(){
        Datum datum = new Datum();
        datum.setName("Priya");
        datum.setType("Dance");
        datum.setPrice(25.5F);
        datum.setShipping(1);
        datum.setUpc("anything");
        datum.setDescription("best");
        datum.setManufacturer("Handmade");
        datum.setModel("Garba");
        datum.setUrl("http:dance academy");
        datum.setImage("img-jpg");
        Datum datum1=given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(datum)
                .post()
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getId());
        idNumber=datum1.getId();
    }

    // verify product created By id
    @Test(priority = 3)
    public void verifyProductById(){
        Datum datum1=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}")
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getName());
    }

    // update product
    @Test(priority = 4)
    public void updateProduct(){
        Datum datum = new Datum();
        datum.setName("Pinky");
        datum.setType("Dance");
        datum.setPrice(25.5F);
        datum.setShipping(1);
        datum.setUpc("everything");
        datum.setDescription("best");
        datum.setManufacturer("Handmade");
        datum.setModel("Garba");
        datum.setUrl("http:dance academy");
        datum.setImage("img-jpg");
        Datum datum1=given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(datum)
                .post()
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getId());
        idNumber=datum1.getId();
    }

    // delete by id
    @Test(priority = 5)
    public void deleteProductById(){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

    //verify product by id after delete
    @Test(priority = 6)
    public void verifyProductDeletedById(){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }

}
