package test;

import baseUrl.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import tastDatas.TestDataJSONPlace;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C21_Put_DeSerialization extends JsonPlaceBaseUrl {

    /*
https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
body’e sahip bir PUT request yolladigimizda donen response’in
response body’sinin asagida verilen ile ayni oldugunu test ediniz

Request Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

Expected Data :

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
 */
    @Test
    public void put01(){
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJSONPlace testDataJSONPlace = new TestDataJSONPlace();

        HashMap<String,Object> reqBody = testDataJSONPlace.requestBodyOlusturMAP();

        HashMap<String,Object> expData = testDataJSONPlace.requestBodyOlusturMAP();

        Response response = given()
                .spec(specJsonPlace).contentType(ContentType.JSON)
                .when()
                .body(reqBody).put("/{pp1}/{pp2}");

        // ASSERTION ISLEMI

        HashMap<String,Object> respMAP = response.as(HashMap.class);

        assertEquals(expData.get("title"),respMAP.get("title"));
        assertEquals(expData.get("body"),respMAP.get("body"));
        assertEquals(expData.get("userId"),respMAP.get("userId"));
        assertEquals(expData.get("id"),respMAP.get("id"));

    }
}
