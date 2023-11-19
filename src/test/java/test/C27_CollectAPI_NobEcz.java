package test;

import baseUrl.CollectAPIBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C27_CollectAPI_NobEcz extends CollectAPIBaseUrl{

    @Test
    public void nobetciEcz(){

        specCollectApi.pathParams("pp1","health","pp2","dutyPharmacy").queryParams("ilce","Üsküdar","il","İstanbul");

        String token = "31fMpsMAVxwMokHnpYL09B:15MLbr5p6RzMF6HNaI5CPr";
        Response response = given().spec(specCollectApi)
                .headers("authorization","apikey " + token)
                .when().get("/{pp1}/{pp2}");
        response.prettyPrint();
    }



}
