package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class C10_Get_ExpectedDataHazirlama {

    /*
 https://jsonplaceholder.typicode.com/posts/22 url'ine
 bir GET request yolladigimizda donen response body'sinin
 asagida verilen ile ayni oldugunu test ediniz



Response body :
 {
 "userId":3,
 "id":22,
 "title":"dolor sint quo a velit explicabo quia nam",
 "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
 }
  */

    @Test
    public void getExp() {

        // 1- Endpoint hazırla
        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // 2- Expected Body hazırlama
        JSONObject expBody = new JSONObject();
        expBody.put("userId",3);
        expBody.put("id",22);
        expBody.put("title","dolor sint quo a velit explicabo quia nam");
        expBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        // 3- Donecek Response kaydet
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Assertion islemi
        JsonPath respJP = response.jsonPath();

        assertEquals(expBody.get("userId"),respJP.get("userId"));
        assertEquals(expBody.get("id"),respJP.get("id"));
        assertEquals(expBody.get("title"),respJP.get("title"));
        assertEquals(expBody.get("body"),respJP.get("body"));
    }

}