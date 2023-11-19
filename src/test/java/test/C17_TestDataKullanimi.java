package test;

import baseUrl.JsonPlaceBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import tastDatas.TestDataJSONPlace;

import static io.restassured.RestAssured.given;

public class C17_TestDataKullanimi extends JsonPlaceBaseUrl {

      /*

https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
request yolladigimizda donen response'in status kodunun 200 ve
response body'sinin asagida verilen ile ayni oldugunu test ediniz

 Response body = Expected Body
  {
  "userId":3,
  "id":22,
  "title":"dolor sint quo a velit explicabo quia nam",
  "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
  um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
  }
   */

    @Test
    public void get01(){

        // 1- EndPoint hazirladik
        specJsonPlace.pathParams("pp1","posts","pp2",22);

        // 2- Expected Data olusturduk
        TestDataJSONPlace testDataJSONPlace = new TestDataJSONPlace();
        JSONObject expData = testDataJSONPlace.expBodyOlusturJSON();

        // 3- Response kaydetmek
        Response response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

        // 4- Assertion Islemi
        JsonPath resJP=response.jsonPath();
        Assert.assertEquals(testDataJSONPlace.okStatusKodu,response.getStatusCode());
        Assert.assertEquals(expData.get("userId"), resJP.get("userId"));
        Assert.assertEquals(expData.get("id"), resJP.get("id"));
        Assert.assertEquals(expData.get("title"), resJP.get("title"));
        Assert.assertEquals(expData.get("body"), resJP.get("body"));



    }

}
