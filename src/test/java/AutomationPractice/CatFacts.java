package AutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {

    @Test
    public void CountingTheSize() {

        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("limit", "332")
                .when().get()
                .then().statusCode(200)
                .log().body().extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> dataFacts = (List<Map<String, Object>>) deserializedResponse.get("data");

        System.out.println(dataFacts.size());

        //more than 50 length  , less than 200  ,  more than 50 and less than 200
        int moreThan50 = 0;
        int lessThan200 = 0;
        int both = 0;
        int catContent = 0;
        for (Map<String, Object> fact : dataFacts) {
            if ((int) fact.get("length") > 50) {
                moreThan50++;
            }
            if ((int) fact.get("length") < 200) {
                lessThan200++;
            }
            if((int) fact.get("length")>50 && (int) fact.get("length")<200){
                both++;
            }
            if(!fact.get("fact").toString().toLowerCase().contains("cat")){
                catContent++;
            }
        }
        System.out.println(moreThan50);
        System.out.println(lessThan200);
        System.out.println(both);
        System.out.println(catContent);
        Assertions.assertEquals(299, moreThan50);
        Assertions.assertEquals(293, lessThan200);
        Assertions.assertEquals(260, both);
        Assertions.assertEquals(25, catContent);


    }
}
