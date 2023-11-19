package test;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import tastDatas.TestDataHerOkuApp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C22_Post_DeSerialization_Odev extends HerOkuAppBaseUrl {

    /*
    n
https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.
Response Body // expected data
{
        "bookingid": 24,
        "booking": {
                    "firstname": "Ahmet",
                    "lastname": "Bulut",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                                     "checkin": "2021-06-01",
                                     "checkout": "2021-06-10"
                                     },
        "additionalneeds": "wi-fi"
        }
        }
        Request body
                    {
                    "firstname" : "Ahmet",
                    "lastname" : “Bulut",
                    "totalprice" : 500,
                    "depositpaid" : false,
                    "bookingdates" : {
                                        "checkin" : "2021-06-01",
                                        "checkout" : "2021-06-10"

                                       },
                    "additionalneeds" : "wi-fi"
                    }
     */

    @Test
    public void post01(){
        specHerOkuApp.pathParams("pp1","booking");

        TestDataHerOkuApp testDataHerOkuApp = new TestDataHerOkuApp();

        HashMap<String,Object> reqBody = testDataHerOkuApp.dataOlusturMAP();

        HashMap<String,Object> expData = testDataHerOkuApp.expectedBodyOlusturMAP();

        Response response = given()
                .spec(specHerOkuApp).contentType(ContentType.JSON)
                .when()
                .body(reqBody).post("/{pp1}");

        // ASSERTION ISLEMI

        HashMap<String,Object> respMAP = response.as(HashMap.class);

        assertEquals(((Map)(expData.get("booking"))).get("firstname"),((Map)(respMAP.get("booking"))).get("firstname"));
        assertEquals(((Map)(expData.get("booking"))).get("lastname"),((Map)(respMAP.get("booking"))).get("lastname"));
        assertEquals(((Map)(expData.get("booking"))).get("totalprice"),((Map)(respMAP.get("booking"))).get("totalprice"));
        assertEquals(((Map)(expData.get("booking"))).get("depositpaid"),((Map)(respMAP.get("booking"))).get("depositpaid"));
        assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkin"),((Map)(((Map)(respMAP.get("booking"))).get("bookingdates"))).get("checkin"));
        assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkout"),((Map)(((Map)(respMAP.get("booking"))).get("bookingdates"))).get("checkout"));
        assertEquals(((Map)(expData.get("booking"))).get("additionalneeds"),((Map)(respMAP.get("booking"))).get("additionalneeds"));
        /*
        {
                    "firstname" : "Ahmet",
                    "lastname" : “Bulut",
                    "totalprice" : 500,
                    "depositpaid" : false,
                    "bookingdates" : {
                    "checkin" : "2021-06-01",
                    "checkout" : "2021-06-
                    10"
                    },
                    "additionalneeds" : "wi-fi"
                    }
         */

    }
}
