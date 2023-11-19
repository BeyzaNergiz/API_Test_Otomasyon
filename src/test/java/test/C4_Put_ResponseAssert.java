package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C4_Put_ResponseAssert {

/*
https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki Json formatindaki body ile
    bir PUT request gonderdigimizde
    {
    "title": "Ahmet",
    "body": "Merhaba",
    "userId": 10,
    "id": 70
    }
    donen Response'un,
    status code'unun 200,
    ve content type'inin application/json; charset=utf-8, ve Server isimli Header'in degerinin cloudflare,
    ve status Line'in HTTP/1.1 200 OK assert ediniz

 */


    @Test
    public void postExp(){
        //1-
        String url="https://restful-booker.herokuapp.com/booking";

        JSONObject innerDate=new JSONObject();
        innerDate.put("checkin","2021-06-01");
        innerDate.put("checkout","2021-06-10");

        JSONObject innerPersonal=new JSONObject();
        innerPersonal.put("firstname","Ahmet");
        innerPersonal.put("lastname","Bulut");
        innerPersonal.put("totalprice",500);
        innerPersonal.put("depositpaid",false);
        innerPersonal.put("bookingdates",innerDate);innerPersonal.put("additionalneeds","wi-fi");

        JSONObject reqBody = new JSONObject();
        reqBody.put("bookingid",24);
        reqBody.put("booking",innerPersonal);

        //2-
        JSONObject innerDates=new JSONObject();
        innerDates.put("checkin","2021-06-01");
        innerDates.put("checkout","2021-06-10");

        JSONObject innerPers=new JSONObject();
        innerPers.put("firstname","Ahmet");
        innerPers.put("lastname","Bulut");
        innerPers.put("totalprice",500);
        innerPers.put("depositpaid",false);
        innerPers.put("bookingdates",innerDates);
        innerPers.put("additionalneeds","wi-fi");

        JSONObject expBody = new JSONObject();
        expBody.put("bookingid",24);
        expBody.put("booking",innerPers);

        //3-
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString()).post(url);

        //4-










    }
}
