package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.*;

public class C7_TekrarlardanKurtulma {
    /*
https://restful-booker.herokuapp.com/booking/10 url'ine bir GET request gonderdigimizde donen Response'un,
        status code'unun 200,
        ve content type'inin application-json, ve
       * response body'sindeki :
        "firstname"in, "Susan",
        ve "lastname"in, "Jackson",
        ve "totalprice"in, 612,
        ve "depositpaid"in, false,
     ve "additionalneeds"in, "Breakfast" oldugunu test edin
 */


    @Test
    public void nonRepeat(){

        // 1- Endpoint hazirla
              String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Soruda verilmiş ise Expected Body hazırla.

        // 3- Donecek Response kaydet

        Response response = given().when().get(url);

        // 4- Assertion yap
        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("firstname",equalTo("Mark"),
                "lastname",equalTo("Jones"),
                "totalprice",equalTo(452),
                "depositpaid",equalTo(true));

    }
}
