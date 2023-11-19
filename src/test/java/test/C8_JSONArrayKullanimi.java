package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class C8_JSONArrayKullanimi {


    /*
        {
        "firstName": "John",
        "lastName": "doe",
        "age": 26,
        "address": {
            "streetAddress": "naist street",
            "city": "Nara",
            "postalCode": "630-0192"
                    },
        "phoneNumbers": [
                        {
                            "type": "iPhone",
                            "number": "0123-4567-8888"
                        },
                        {
                            "type": "home",
                            "number": "0123-4567-8910"
                        }
                        ]
        }
         */

    @Test
    public void jsonArray(){

        JSONObject ceptel = new JSONObject();
        ceptel.put("type","iPhone");
        ceptel.put("number","0123-4567-8888");

        JSONObject evtel = new JSONObject();
        evtel.put("type","home");
        evtel.put("number","0123-4567-8910");

        JSONArray phoneNumbers = new JSONArray();
        phoneNumbers.put(0,ceptel);
        phoneNumbers.put(1,evtel);

        JSONObject adres = new JSONObject();
        adres.put("streetAddress","naist street");
        adres.put("city","Nara");
        adres.put("postalCode","630-0192");


        JSONObject personalInfo = new JSONObject();
        personalInfo.put("firstName","John");
        personalInfo.put("lastName","Doe");
        personalInfo.put("age",26);
        personalInfo.put("address",adres);
        personalInfo.put("phoneNumbers",phoneNumbers);

        System.out.println("personelInfo : " + personalInfo);

        System.out.println("Kisi adi : " + personalInfo.get("firstName"));
        System.out.println("Kisi soyadi : " + personalInfo.get("lastName"));
        System.out.println("Kisi yasi : " + personalInfo.get("age"));
        System.out.println("Kisi adres - sokak : " + personalInfo.getJSONObject("address").get("streetAddress"));
        System.out.println("Kisi adres - sehir : " + personalInfo.getJSONObject("address").get("city"));
        System.out.println("Kisi adres - posta kodu : " + personalInfo.getJSONObject("address").get("postalCode"));
        System.out.println("Kisi telefon 1 Tur : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));
        System.out.println("Kisi telefon 1 Number : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));
        System.out.println("Kisi telefon 2 Tur : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("type"));
        System.out.println("Kisi telefon 2 Number : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));

    }
}
