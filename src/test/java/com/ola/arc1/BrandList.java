package com.ola.arc1;

import com.ola.util.AJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BrandList {

    @BeforeClass
    public static String getBrandList(){

        RestAssured.baseURI = "https://automationexercise.com";
        Response response = RestAssured.get("/api/brandsList");
        String res= response.asString();
        System.out.println(res);
        return res;
    }

    @Test
    public void BrandListStatusCode(){
        JsonPath js = AJson.gtJson(BrandList.getBrandList());
        int status = js.getInt("responseCode");
        Assert.assertEquals(status,200);

    }
    @Test
    public void BrandListnotEmpty(){
        JsonPath js = AJson.gtJson(BrandList.getBrandList());
        List<String> ls = js.getList("brands");

        Assert.assertTrue(!ls.isEmpty());

    }



    @Test
    public void getBrandListSize(){
        JsonPath js = AJson.gtJson(BrandList.getBrandList());
        List<String> ls = js.getList("brands");

        Assert.assertEquals(ls.size(), 34);

    }
}
