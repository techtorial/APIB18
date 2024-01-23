package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Airport {


    @Test
    public void validateAirportInformation(){

        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="api/airports";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();

        JsonPath deserializedResponse=response.jsonPath();
        Assertions.assertEquals("GKA",deserializedResponse.getString("data[0].id"));
        Assertions.assertEquals("airport",deserializedResponse.getList("data.type").get(1));
        Assertions.assertEquals("airport",deserializedResponse.getList("data.type").getFirst());
        Assertions.assertEquals("Goroka Airport",deserializedResponse.getString("data[0].attributes.name"));
    }

    @Test
    public void validateAirportInformationWithMatchMaker(){

        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="api/airports";

         RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200)
                 .body("data[0].id", Matchers.equalTo("GKA"))
                 .body("data[0].attributes.name",Matchers.equalTo("Goroka Airport"));
    }



}
