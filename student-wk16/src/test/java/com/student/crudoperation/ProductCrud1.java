package com.student.crudoperation;

import com.student.model.Datum;
import com.student.model.Products;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductCrud1 {
    static String name = "Baloon (2 pack)";
    static String type = "Soft";
    static double price = 4.99;
    static String upc = "011";
    static int shipping = 1;
    static String description = "celebrations";
    static String manufacturer = "Rubber";
    static String model = "MN2400B4Y";
    static String url = "http://www.bestbuy.com";
    static String image = "image-jpg";

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

    // create new product
    @Test(priority = 2)
    public void createNewProduct(){
        Datum datum = new Datum();
        datum.setName(name);
        datum.setType(type);
        datum.setPrice((float) price);
        datum.setShipping(shipping);
        datum.setUpc(upc);
        datum.setDescription(description);
        datum.setManufacturer(manufacturer);
        datum.setModel(model);
        datum.setUrl(url);
        datum.setImage(image);
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
        datum.setName("party");
        datum.setType("themes");
        datum.setPrice((float)price);
        datum.setShipping(shipping);
        datum.setUpc(upc);
        datum.setDescription(description);
        datum.setManufacturer(manufacturer);
        datum.setModel(model);
        datum.setUrl(url);
        datum.setImage(image);
        Response response=given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(datum)
                .put("/{id}");
        response.then().statusCode(200);
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
