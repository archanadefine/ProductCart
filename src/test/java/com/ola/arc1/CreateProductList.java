package com.ola.arc1;

import com.ola.util.AJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductList {

    @BeforeClass

    public static String cList() {

        RestAssured.baseURI = "https://automationexercise.com";
        Response response = RestAssured.post("/api/productsList");
        response.then().assertThat().statusCode(200);
        String res= response.asString();
        System.out.println(res);
        return res;
    }

    @Test
    public void getStatusCode(){
        JsonPath js = AJson.gtJson(CreateProductList.cList());
        int statuscode = js.getInt("responseCode");
        Assert.assertEquals(statuscode,405);

    }

    @Test
    public void getmessage(){
        JsonPath js = AJson.gtJson(CreateProductList.cList());
        String msg= js.getString("message");
        Assert.assertEquals(msg,"This request method is not supported.");

    }


}
