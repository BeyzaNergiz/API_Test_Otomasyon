package test;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPOJO;
import pojos.BookingPOJO;
import pojos.HerOkuAppPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_PojoClass extends HerOkuAppBaseUrl {

     /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ali",
    	                "lastname" : “Bak",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }


    	            	Response Body = Expected Data
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
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */

    @Test
    public void post01(){
        specHerOkuApp.pathParam("pp1","booking");
        BookingDatesPOJO bookingdates=new BookingDatesPOJO("2021-06-01","2021-06-10");
        BookingPOJO reqBody = new BookingPOJO("Ali","Bak",500,false,bookingdates,"wi-fi");

        HerOkuAppPOJO expBody=new HerOkuAppPOJO(1364,reqBody);

        Response response=given()
                .spec(specHerOkuApp)
                .contentType(ContentType.JSON)
                .when().body(reqBody)
                .post("/{pp1}");

        HerOkuAppPOJO respPOJO=response.as(HerOkuAppPOJO.class);

        //assertEquals(expBody.getBookingid(),respPOJO.getBookingid());
        assertEquals(expBody.getBooking().getFirstname(),respPOJO.getBooking().getFirstname());
        assertEquals(expBody.getBooking().getLastname(),respPOJO.getBooking().getLastname());
        assertEquals(expBody.getBooking().getTotalprice(),respPOJO.getBooking().getTotalprice());
        assertEquals(expBody.getBooking().isDepositpaid(),respPOJO.getBooking().isDepositpaid());
        assertEquals(expBody.getBooking().getBookingdates().getCheckin(),respPOJO.getBooking().getBookingdates().getCheckin());
        assertEquals(expBody.getBooking().getBookingdates().getCheckout(),respPOJO.getBooking().getBookingdates().getCheckout());
        assertEquals(expBody.getBooking().getAdditionalneeds(),respPOJO.getBooking().getAdditionalneeds());



    }
}
