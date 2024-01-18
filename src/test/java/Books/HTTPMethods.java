package Books;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class HTTPMethods {

    @Test
    public void authorization() {
        /*
        1-With RestAssured Library,I want you validate the status code
         */
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "auth";

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post().then().statusCode(200).log().body()
                .extract().response();

        Map<String, String> deserializedResponse = response.as(new TypeRef<Map<String, String>>() {
        });
        System.out.println(deserializedResponse.get("token"));
        Assertions.assertNotNull(deserializedResponse.get("token"));
    }

    @Test
    public void getSingleBookID() {
        /*
        1-I want you deserialized the endpoint https://restful-booker.herokuapp.com/booking/447
        2-I want you validate booking_id is not null
         */
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking";

        Response response = RestAssured.given()
                .accept("application/json")
                .queryParam("firstname", "AhmetBal")
                .queryParam("lastname", "Baldir")
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();
    }

    @Test
    public void getBookIdInformation() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/400";

        Response response = RestAssured.given()
                .accept("application/json")
                .when().get()
                .then().statusCode(200).log().body()
                .extract().response();
        //Deserialization
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Map<String, Object> deserializedResponseDating = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assertions.assertEquals("John", deserializedResponse.get("firstname"));
        Assertions.assertEquals("Smith", deserializedResponse.get("lastname"));
        Assertions.assertEquals(111, deserializedResponse.get("totalprice"));
        Assertions.assertEquals(true, deserializedResponse.get("depositpaid"));
        Assertions.assertEquals("Breakfast", deserializedResponse.get("additionalneeds"));
        Assertions.assertEquals("2018-01-01", deserializedResponseDating.get("checkin"));
        Assertions.assertEquals("2019-01-01", deserializedResponseDating.get("checkout"));
    }

    @Test
    public void createBook() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking";
        Response response = RestAssured.given().
                contentType(ContentType.JSON)
                .accept("application/json")
                .auth().basic("admin","password123")
                .body("{\n" +
                        "    \"firstname\" : \"AhmetBal\",\n" +
                        "    \"lastname\" : \"Baldir\",\n" +
                        "    \"totalprice\" : 99999,\n" +
                        "    \"depositpaid\" : 123,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2023-01-01\",\n" +
                        "        \"checkout\" : \"2024-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Teaching\"\n" +
                        "}")
                .when().post().then().statusCode(200).log().body()
                .extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assertions.assertNotNull(deserializedResponse.get("bookingid"));
        Map<String, Object> deserializedBooking = (Map<String, Object>) deserializedResponse.get("booking");
        Assertions.assertEquals("AhmetBal", deserializedBooking.get("firstname"));
        Assertions.assertEquals("Baldir", deserializedBooking.get("lastname"));
        Assertions.assertEquals(99999, deserializedBooking.get("totalprice"));
        Assertions.assertEquals(true, deserializedBooking.get("depositpaid"));
        Map<String, Object> deserializedBookingDates = (Map<String, Object>) deserializedBooking.get("bookingdates");
        Assertions.assertEquals("2023-01-01", deserializedBookingDates.get("checkin"));
        Assertions.assertEquals("2024-01-01", deserializedBookingDates.get("checkout"));
    }

    @Test
    public void updateBook() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/2153";

        Response response = RestAssured.given().
                header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body("{\n" +
                        "    \"firstname\" : \"AhmetBal\",\n" +
                        "    \"lastname\" : \"Baldirs\",\n" +
                        "    \"totalprice\" : 250,\n" +
                        "    \"depositpaid\" : false,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Learning\"\n" +
                        "}")
                .when().put().then().statusCode(200).log().body()
                .extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Assertions.assertEquals("AhmetBal", deserializedResponse.get("firstname"));
        Assertions.assertEquals("Baldirs", deserializedResponse.get("lastname"));
        Assertions.assertEquals(250, deserializedResponse.get("totalprice"));
        Assertions.assertEquals(false, deserializedResponse.get("depositpaid"));
        Map<String, Object> deserializedBookingDates = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assertions.assertEquals("2018-01-01", deserializedBookingDates.get("checkin"));
        Assertions.assertEquals("2019-01-01", deserializedBookingDates.get("checkout"));

    }

}
