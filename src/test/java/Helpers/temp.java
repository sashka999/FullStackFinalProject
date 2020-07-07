package Helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;

public class temp {

    String url = "http://localhost:9500/";
    RequestSpecification httpRequest;
    Response response;

    @Test
    public void testing(){

        JSONObject params = new JSONObject();
        params.put("name", "AlexTeam_2");
        params.put("email", "3@3.com");

        RestAssured.baseURI = url;
        httpRequest = RestAssured.given().auth().preemptive().basic("admin", "admin");
        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(params.toJSONString());
        response = httpRequest.delete("/api/teams/2");

        System.out.println(response.prettyPrint());
    }
}
