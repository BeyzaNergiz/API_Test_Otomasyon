package test;

import baseUrl.CollectAPIBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C28_CollectApiNEWS extends CollectAPIBaseUrl {

    @Test
    public void nobetciEcz(){

        specCollectApi.pathParams("pp1","news","pp2","getNews").queryParams("country","tr","tag","general");

        String token = "31fMpsMAVxwMokHnpYL09B:15MLbr5p6RzMF6HNaI5CPr";
        Response response = given().spec(specCollectApi)
                .headers("authorization","apikey " + token)
                .when().get("/{pp1}/{pp2}");
        response.prettyPrint();
    }

}
