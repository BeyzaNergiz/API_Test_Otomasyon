package test;

import baseUrl.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import tastDatas.TestDataJSONPlace;

import static io.restassured.RestAssured.given;

public class C18_Put_TestDataClassKullanimi extends JsonPlaceBaseUrl {

    /*
  https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body'e sahip bir PUT
  request yolladigimizda donen response'in
  status kodunun 200, content type'inin "application/json; charset=utf-8",
  Connection header degerinin "keep-alive"
  ve response body'sinin asagida verilen ile ayni oldugunu test ediniz

  Request Body

      {
      "title":"Ali",
      "body":"Merhaba",
      "userId":10,
      "id":70
      }

  Expected Data

      {
      "title":"Ali",
      "body":"Merhaba",
      "userId":10,
      "id":70
      }
*/

    @Test
    public void put01(){

        // 1- EndPoint hazırlama
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        // 2- Expected Data olusturma
        TestDataJSONPlace testDataJSONPlace = new TestDataJSONPlace();
        JSONObject reqBody = testDataJSONPlace.reqBodyOlusturJSON();
        JSONObject expData = testDataJSONPlace.reqBodyOlusturJSON();


        // 3- Response kaydetmek
        Response response = given().spec(specJsonPlace)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(reqBody.toString())
                        .put("/{pp1}/{pp2}");

        // 4- Assertion Islemi
        JsonPath resJP=response.jsonPath();

        Assert.assertEquals(testDataJSONPlace.okStatusKodu,response.getStatusCode());
        Assert.assertEquals(testDataJSONPlace.contentType,response.getContentType());
        Assert.assertEquals(testDataJSONPlace.connectionHeader,response.getHeader("Connection"));
        Assert.assertEquals(reqBody.get("title"), resJP.get("title"));
        Assert.assertEquals(reqBody.get("body"), resJP.get("body"));
        Assert.assertEquals(reqBody.get("userId"), resJP.get("userId"));
        Assert.assertEquals(reqBody.get("id"), resJP.get("id"));
    }
}
