package test;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import tastDatas.TestDataDummy;


import static io.restassured.RestAssured.given;

public class C19_Get_TestDataKullanimiOdev extends DummyBaseUrl {
    /*

            C20_Get_TestDataKullanimi
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un status code’unun 200, content Type’inin
        application/json ve body’sinin asagidaki gibi oldugunu test edin.
        Response Body
        {
        "status": "success",
        "data": {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
                 },
        "message": "Successfully! Record has been fetched."
        }
     */

    @Test
    public void get01(){

        // 1- EndPoint hazirlama
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2- Expected Data olusturma
        TestDataDummy testDataDummy = new TestDataDummy();
        JSONObject expData = testDataDummy.reqBodyOlusturJSON();

        // 3- Response kaydetme
        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

        // 4- Assertion Islemi
        JsonPath resJP=response.jsonPath();

        Assert.assertEquals(testDataDummy.okStatusKodu,response.getStatusCode());
        Assert.assertEquals(testDataDummy.contentType,response.getContentType());
        Assert.assertEquals(expData.getJSONObject("data").get("id"), resJP.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"), resJP.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"), resJP.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"), resJP.get("data.employee_age"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"), resJP.get("data.profile_image"));
        Assert.assertEquals(expData.get("message"), resJP.get("message"));


    }

}
