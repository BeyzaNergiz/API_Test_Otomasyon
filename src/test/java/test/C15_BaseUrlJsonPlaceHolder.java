package test;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C15_BaseUrlJsonPlaceHolder extends HerOkuAppBaseUrl {

    //Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    //1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
    //gonderdigimizde donen response’un status code’unun 200 oldugunu ve
    //Response’ta 12 booking oldugunu test edin
    //2- https://restful-booker.herokuapp.com/booking
    //endpointine asagidaki body’ye sahip bir POST
    //request gonderdigimizde donen response’un
    //status code’unun 200 oldugunu ve “firstname”
    //degerinin “Ahmet” oldugunu test edin
    /*
    {
            "firstname" : "Jane",
            "lastname" : “Doe",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                                "checkin" : "2021-06-01",
                                "checkout" : "2021-06-10"
                              },
            "additionalneeds" : "Extra pillows please"
            }
     */


    @Test
    public void get01(){

        ///1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
        //gonderdigimizde donen response’un status code’unun 200 oldugunu ve
        //Response’ta booking totalprice si 111 oldugunu test edin

        specHerOkuApp.pathParam("pp1","booking");

        Response response = given().spec(specHerOkuApp).when().get("/{pp1}");

        response.then().assertThat().statusCode(200)
                .body("totalprice", equalTo(111));



    }



    @Test
    public void post02(){
        //2- https://restful-booker.herokuapp.com/booking
        //endpointine asagidaki body’ye sahip bir POST
        //request gonderdigimizde donen response’un
        //status code’unun 200 oldugunu ve “firstname”
        //degerinin “Ahmet” oldugunu test edin

        specHerOkuApp.pathParams("pp1","booking");

        JSONObject innerDate = new JSONObject();
        innerDate.put("checkin","2021-06-01");
        innerDate.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Jane");
        reqBody.put("lastname","Doe");
        reqBody.put("totalprice",111);
        reqBody.put("depositpaid",true);
        reqBody.put("bookingdates",innerDate);
        reqBody.put("additionalneeds","Extra pillows please");



        Response response = given().spec(specHerOkuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");

        response.then().assertThat().statusCode(200)
                .body("firstname", equalTo("Jane"));

    }

}
