package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.StarWarsCharacterPojo;
import pojo.StarWarsPlanetsPojo;

import java.util.Arrays;
import java.util.List;

public class StarWars {

    @Test
    public void validateInformation(){
        RestAssured.baseURI="https://swapi.dev";
        RestAssured.basePath="api/people/1";

        Response response = RestAssured.given()
                .accept("application/json")
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();
        StarWarsCharacterPojo deserializedCharacters=response.as(StarWarsCharacterPojo.class);

        System.out.println(deserializedCharacters.getName());
        Assertions.assertEquals("Luke Skywalker",deserializedCharacters.getName(),"SW character is failing");
        Assertions.assertEquals("172",deserializedCharacters.getHeight());
        Assertions.assertEquals("blue",deserializedCharacters.getEye_color());
        Assertions.assertEquals("male",deserializedCharacters.getGender());
        Assertions.assertEquals("https://swapi.dev/api/planets/1/",deserializedCharacters.getHomeworld());
        Assertions.assertEquals(4,deserializedCharacters.getFilms().size());
        List<String> expectedVehicles= Arrays.asList("https://swapi.dev/api/vehicles/14/","https://swapi.dev/api/vehicles/30/");
        for(int i=0;i<deserializedCharacters.getVehicles().size();i++){
            Assertions.assertEquals(expectedVehicles.get(i),deserializedCharacters.getVehicles().get(i));
        }
    }

    @Test
    public void swPlanetTest(){

        RestAssured.baseURI="https://swapi.dev";
        RestAssured.basePath="api/planets/1";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();

       StarWarsPlanetsPojo deserialedPlanetsInformation= response.as(StarWarsPlanetsPojo.class);
        Assertions.assertEquals("Tatooine",deserialedPlanetsInformation.getName());
        Assertions.assertEquals("arid",deserialedPlanetsInformation.getClimate());
        Assertions.assertEquals("desert",deserialedPlanetsInformation.getTerrain());

    }
}
