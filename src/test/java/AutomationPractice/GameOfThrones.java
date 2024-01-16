package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameOfThrones {

    @Test
    public void ValidateSpecificCharacter(){
        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="/api/v2/Characters/10";
       Response response= RestAssured.given()
                .header("Accept","application/json")
                .when()
                .get()
                .then().statusCode(200).log().body().extract().response();
           Map<String,Object> deseriliazedCharacter=response.as(new TypeRef<Map<String, Object>>() {});
        Assertions.assertEquals(10,deseriliazedCharacter.get("id"));
        Assertions.assertEquals("Cateyln",deseriliazedCharacter.get("firstName"));
        Assertions.assertEquals("Stark",deseriliazedCharacter.get("lastName"));
        Assertions.assertEquals("Catelyn Stark",deseriliazedCharacter.get("fullName"));
        Assertions.assertEquals("Lady of Winterfell",deseriliazedCharacter.get("title"));
        Assertions.assertEquals("House Stark",deseriliazedCharacter.get("family"));
        Assertions.assertEquals("catelyn-stark.jpg",deseriliazedCharacter.get("image"));
        Assertions.assertEquals("https://thronesapi.com/assets/images/catelyn-stark.jpg",deseriliazedCharacter.get("imageUrl"));
        }
    }
