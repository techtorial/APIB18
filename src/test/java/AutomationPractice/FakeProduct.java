package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FakeProduct {

    @Test
    public void validateSingleProductInformation(){
       /*
       1-Defined the Url(endpoint)--> https://fakestoreapi.com/products/1
       2-Check the HTTP Request(Methods) -->GET,POST,PUT,PATCH,DELETE
       3-Check the header -->Content-Type=application/json, Accept ,Authorization etc
       4-Check you need query(filtration) or path parameters(end point)
       5-Send the request then validate STATUS CODE
        */
        Response response=RestAssured.given()//given is referring what is precondition for the end point
                .header("Accept","application/json")
                .when()
                .get("https://fakestoreapi.com/products/1")
                .then()
                .log().body()
                .statusCode(200).extract().response();
        //Once you store the response payload into response reference,
        //You are ready to deserialize the Json object to Java(map) code.
        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(deserializedResponse);
        Assertions.assertEquals(1,deserializedResponse.get("id"));
        Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",deserializedResponse.get("title"));
        Assertions.assertEquals(109.95,deserializedResponse.get("price"));
        Assertions.assertEquals("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",deserializedResponse.get("description"));
        Assertions.assertEquals("men's clothing",deserializedResponse.get("category"));
        Assertions.assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",deserializedResponse.get("image"));
    }

    @Test
    public void validateSingleProductInformation2(){
        Response response=RestAssured.given()//given is referring what is precondition for the end point
                .header("Accept","application/json")
                .pathParams("section","products")
                .pathParams("productId",1)
                .when()
                .get("https://fakestoreapi.com/{section}/{productId}")
                .then()
                .log().body()
                .statusCode(200).extract().response();
        //Once you store the response payload into response reference,
        //You are ready to deserialize the Json object to Java(map) code.
        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(deserializedResponse);
        Assertions.assertEquals(1,deserializedResponse.get("id"));
        Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",deserializedResponse.get("title"));
        Assertions.assertEquals(109.95,deserializedResponse.get("price"));
        Assertions.assertEquals("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",deserializedResponse.get("description"));
        Assertions.assertEquals("men's clothing",deserializedResponse.get("category"));
        Assertions.assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",deserializedResponse.get("image"));
    }

    @Test
    public void validateSingleProductInformation3(){
                 RestAssured.baseURI="https://fakestoreapi.com";
                 RestAssured.basePath="products/1";
        Response response=RestAssured.given()//given is referring what is precondition for the end point
                .header("Accept","application/json")
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200).extract().response();
        //Once you store the response payload into response reference,
        //You are ready to deserialize the Json object to Java(map) code.
        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

        System.out.println(deserializedResponse);
        Assertions.assertEquals(1,deserializedResponse.get("id"));
        Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",deserializedResponse.get("title"));
        Assertions.assertEquals(109.95,deserializedResponse.get("price"));
        Assertions.assertEquals("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",deserializedResponse.get("description"));
        Assertions.assertEquals("men's clothing",deserializedResponse.get("category"));
        Assertions.assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",deserializedResponse.get("image"));
        Map<String,Object> ratingInformation= (Map<String, Object>) deserializedResponse.get("rating");
        System.out.println(ratingInformation);
        Assertions.assertEquals(3.9,ratingInformation.get("rate"));
        Assertions.assertEquals(120,ratingInformation.get("count"));

    }
}
