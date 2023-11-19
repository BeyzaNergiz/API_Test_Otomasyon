package test;

import baseUrl.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceRequestBodyPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_PUT_PojoClassKullanimi extends JsonPlaceBaseUrl {

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

    Response Body Expected
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

        JsonPlaceRequestBodyPOJO reqData = new JsonPlaceRequestBodyPOJO("Ahmet","Merhaba",10,70);

        JsonPlaceRequestBodyPOJO expData = new JsonPlaceRequestBodyPOJO("Ahmet","Merhaba",10,70);

        Response response=given().spec(specJsonPlace).contentType(ContentType.JSON).when().body(reqData).put("/{pp1}/{pp2}");

        JsonPlaceRequestBodyPOJO respPOJO=response.as(JsonPlaceRequestBodyPOJO.class);

        assertEquals(expData.getTitle(),respPOJO.getTitle());
        assertEquals(expData.getId(),respPOJO.getId());
        assertEquals(expData.getUserId(),respPOJO.getUserId());
        assertEquals(expData.getBody(),respPOJO.getBody());

    }
}
