package test;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import tastDatas.TestDataHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class C20_Post_TestDataKullanimi extends HerOkuAppBaseUrl {

    /*
   https://restful-booker.herokuapp.com/booking url'ine asagidaki body'ye sahip
   bir POST request gonderdigimizde donen response'un status kodunu 200 ve id haric
   body'sinin asagidaki gibi oldugunu test edin.

Request body
      {
      "firstname" : "Ali",
      "lastname" : "Bak",
      "totalprice" : 500,
      "depositpaid" : false,
      "bookingdates" : {
                     "checkin" : "2021-06-01",
                     "checkout" : "2021-06-10"
                       },
      "additionalneeds" : "wi-fi"
       }

Expected Body
{
   "bookingid":24,
   "booking":{
           "firstname":"Ali",
           "lastname":"Bak",
           "totalprice":500,
           "depositpaid":false,
           "bookingdates":{
                           "checkin":"2021-06-01",
                           "checkout":"2021-06-10"
                           },
           "additionalneeds":"wi-fi"
              }
   }
    */

    @Test
    public void post01(){

        // 1- EndPoint hazırlama
        specHerOkuApp.pathParams("pp1","booking");

        // 2- Expected Data olusturma
        TestDataHerOkuApp testDataHerOkuApp = new TestDataHerOkuApp();
        JSONObject reqBody = testDataHerOkuApp.reqBodyOlusturJSON();
        JSONObject expBody = testDataHerOkuApp.expectedBodyOlusturJSON();


        // 3- Response kaydetmek
        Response response = given().spec(specHerOkuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");

        // 4- Assertion Islemi
        JsonPath respJP=response.jsonPath();


        assertEquals(testDataHerOkuApp.okStatusKodu,response.getStatusCode());
        assertEquals(expBody.getJSONObject("booking").get("firstname"),respJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),respJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),respJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),respJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),respJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),respJP.get("booking.bookingdates.checkout"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),respJP.get("booking.additionalneeds"));
    }
}
