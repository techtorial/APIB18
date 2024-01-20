package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.BookPojo;

public class Books {

    @Test
    public void validateBookInformationWithPojo(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="booking/1099";

        Response response = RestAssured.given()
                .accept("application/json")
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();

        BookPojo deserializedResponse=response.as(BookPojo.class);
        Assertions.assertEquals("AhmetBal",deserializedResponse.getFirstname());
        Assertions.assertEquals("Baldir",deserializedResponse.getLastname());
        Assertions.assertEquals(99999,deserializedResponse.getTotalprice());
        Assertions.assertTrue(deserializedResponse.isDepositpaid());
        Assertions.assertEquals("Teaching",deserializedResponse.getAdditionalneeds());
        Assertions.assertEquals("2023-01-01",deserializedResponse.getBookingdates().getCheckin());
        Assertions.assertEquals("2024-01-01",deserializedResponse.getBookingdates().getCheckout());

    }
}
