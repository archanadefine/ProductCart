package com.ola.util;

import io.restassured.path.json.JsonPath;

public class AJson {

    public static JsonPath gtJson(String response) {
        JsonPath json = new JsonPath(response);
        return json;
    }
}
