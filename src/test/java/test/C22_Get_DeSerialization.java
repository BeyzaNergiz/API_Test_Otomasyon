package test;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import tastDatas.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Get_DeSerialization extends DummyBaseUrl {

          /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET
    request gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi
    oldugunu test edin.

    Response Body
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get01(){
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        TestDataDummy testDataDummy=new TestDataDummy();

        HashMap<String,Object> expBody=testDataDummy.expectedBodyOlusturMAP();

        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

        HashMap<String,Object> respMAP=response.as(HashMap.class);

        assertEquals(testDataDummy.okStatusKodu,response.getStatusCode());
        assertEquals(testDataDummy.contentType,response.getContentType());
        assertEquals(expBody.get("status"),respMAP.get("status"));
        assertEquals(expBody.get("message"),respMAP.get("message"));

        assertEquals(((Map)(expBody.get("data"))).get("id"),((Map)(respMAP.get("data"))).get("id"));
        assertEquals(((Map)(expBody.get("data"))).get("employee_name"),((Map)(respMAP.get("data"))).get("employee_name"));
        assertEquals(((Map)(expBody.get("data"))).get("employee_salary"),((Map)(respMAP.get("data"))).get("employee_salary"));
        assertEquals(((Map)(expBody.get("data"))).get("employee_age"),((Map)(respMAP.get("data"))).get("employee_age"));
        assertEquals(((Map)(expBody.get("data"))).get("profile_image"),((Map)(respMAP.get("data"))).get("profile_image"));

        // id içinde de bir bilgi olsaydı;
        //assertEquals(((Map)(((Map)(expBody.get("data"))).get("id"))).get("number"),((Map)(respMAP.get("data"))).get("id"));






    }
}
