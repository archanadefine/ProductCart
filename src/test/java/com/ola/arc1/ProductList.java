
package com.ola.arc1;

import com.ola.util.AJson;
import io.restassured.RestAssured;

import static org.testng.Assert.assertTrue;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public class ProductList{

    @BeforeClass

   public static String pList() {

        RestAssured.baseURI = "https://automationexercise.com";
        Response response = RestAssured.get("/api/productsList");
        response.then().assertThat().statusCode(200);
        String res= response.asString();
        return res;
    }

    @Test
    public void getStatusCode(){
        JsonPath js = AJson.gtJson(ProductList.pList());
        Assert.assertEquals(js.getInt("responseCode"), 200);
    }



    @Test
    public void getPriceOfProductName(){
        JsonPath js = AJson.gtJson(ProductList.pList());
        int count = js.getInt("products.size()");
        for(int i=0;i<count;i++) {
            if (Objects.equals(js.getString("products.name"), "Madame Top For Women")) {
                String price = js.getString("products.price");
                Assert.assertEquals(price, "Rs. 1000");
            }

        }}

    @Test
    public void brandList() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        List<String> brands = js.getList("products.brand"); // Extracting brand list directly

        HashSet<String> uniqueBrands = new HashSet<>(brands); // Using HashSet to store unique brands
        for (String brand : uniqueBrands) {
            System.out.println(brand);
        }
        Assert.assertEquals(uniqueBrands.size(), 8);
    }

    @Test
    public void categoryList() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        List<String> category = js.getList("products.category.category");

        HashSet<String> uniqueCategory = new HashSet<>(category);
        for (String cat : uniqueCategory) {
            System.out.println(cat);
        }
        Assert.assertEquals(uniqueCategory.size(), 6);
    }

    @Test
    public void usrTypeList() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        List<String> usertype = js.getList("products.category.usertype.usertype");

        HashSet<String> uniqueUserType= new HashSet<>(usertype);
        for (String utype : uniqueUserType) {
            System.out.println(utype);
        }
        Assert.assertEquals(uniqueUserType.size(), 3);
    }

    @Test
    public void priceForID() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        int count = js.getInt("products.size()");
       for (int i = 0; i < count; i++) {

           int id = js.getInt("products["+i+"].id");
           if(id==7){
               String pp= js.getString("products.price");
               System.out.println(pp);
           }

       }
    }

    @Test
    public void getBrandPriceforUserType() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        int count = js.getInt("products.size()");
        for (int i = 0; i < count; i++) {

            String uType = js.getString("products["+i+"].category.usertype.usertype");
            if(uType.equalsIgnoreCase("Women")) {
                List<String> brand = js.getList("products.brand");
                HashSet<String> uniquebrand= new HashSet<>(brand);
                for (String ubrand : uniquebrand) {
                    System.out.println(ubrand);
                }
                List<String> price = js.getList("products.price");
                HashSet<String> uniqueprice= new HashSet<>(price);
                for (String uprice : uniqueprice) {
                    System.out.println(uprice);
                }

            }
        }
    }

    @Test
    public void listNotEmpty() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        Boolean flag;
        int count = js.getInt("products.size()");
        if (count > 0) {
            flag = true;

        }else{
                flag = false;
            }

            assertTrue(true, String.valueOf(flag));
        }

    @Test
    public void listSizeMoreThenTwo() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        Boolean flag;
        int count = js.getInt("products.size()");
        if (count > 2) {
            flag = true;

        }else{
            flag = false;
        }

        assertTrue(true, String.valueOf(flag));
    }

    @Test
    public void getLastIdDetails() {
        JsonPath js = AJson.gtJson(ProductList.pList());
        int count = js.getInt("products.size()");
        //String str=  "products["+(count-1)+"].id";
        Assert.assertEquals(js.getInt("products["+(count-1)+"].id"),43);


    }

    @Test
    public void attributeDataType() {
        JsonPath js = AJson.gtJson(ProductList.pList());

            Object name =js.get("products[0].name");
            //System.out.println(name.getClass());
        assertTrue(name instanceof String);

            Object price =js.get("products[0].price");
             assertTrue(price instanceof String);

        Object brand =js.get("products[0].brand");
        assertTrue(brand instanceof String);
        Object category=js.get("products[0].category.category");
        assertTrue(category instanceof String);

        Object usertype =js.get("products[0].category.usertype.usertype");
        System.out.println(usertype);
        assertTrue(usertype instanceof String);


        }

    }









