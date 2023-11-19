package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C2_Get_ApiSorgulama_Assertion {

    /*
           https://restful-booker.herokuapp.com/booking/83 url'ine bir GET request
        onderdigimizde donen Response'un,
        status code'unun 200,
        e content type'inin application/json; charset=utf-8, ve Server isimli Header'in degerinin Cowboy,
        e status Line'in HTTP/1.1 200 OK olduğunu assert ediniz.
 */

    @Test
    public void getAssert(){

        // 1- url belirleyip url adresine gideceğiz
        String url = "https://restful-booker.herokuapp.com/booking/16";

        // 2- expected data verilmişse expected data hazırlanır
        // 3- Actual data alınacak
        Response response = given().when().get(url);
        response.then()
                .assertThat()
                .statusCode(200).header("Server","Cowboy")
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");



    }


}
