package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.PetStorePojo;
import utils.PayLoadUtils;

public class PetStore {

    @Test
    public void validateCreateApiPet(){
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        RestAssured.basePath="pet";
        Response response = RestAssured.given().contentType("application/json")
                .accept("application/json")
                .body(PayLoadUtils.getPetPayload("Wolfs","King","Single")).when().post()
                .then().log().body().statusCode(200).extract().response();

        PetStorePojo deserializedResponse=response.as(PetStorePojo.class);

//        Assertions.assertEquals(999,deserializedResponse.getId());
        Assertions.assertEquals("King",deserializedResponse.getName());
        Assertions.assertEquals("www.amazon.com/bird.png",deserializedResponse.
                getPhotoUrls().get(0));
        Assertions.assertEquals("Single",deserializedResponse.getStatus());
        Assertions.assertEquals("Wolfs",deserializedResponse.getCategory().getName());
        Assertions.assertEquals("unknown",deserializedResponse.getTags().get(0).getName());
    }

    @Test
    public void validateCreateApiPetWithJsonPath(){
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        RestAssured.basePath="pet";
        Response response = RestAssured.given().contentType("application/json")
                .accept("application/json")
                .body(PayLoadUtils.getPetPayload("Wolfs","King","Single")).when().post()
                .then().log().body().statusCode(200).extract().response();

        JsonPath deserializedResponse=response.jsonPath();

        Assertions.assertEquals("King",deserializedResponse.getString("name"));
        Assertions.assertEquals("www.amazon.com/bird.png",deserializedResponse.getList("photoUrls").get(0));
        Assertions.assertEquals("unknown",deserializedResponse.getList("tags.name").get(0));
        Assertions.assertEquals("Single",deserializedResponse.getString("status"));

    }











}
