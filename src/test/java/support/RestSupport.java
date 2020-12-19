package support;
import static com.jayway.restassured.RestAssured.given;
import java.util.Map;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.RequestSpecificationImpl;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import groovy.json.internal.LazyMap;
import steps.Hooks;

public class RestSupport {
    private static RequestSpecification requestSpecification = given()
            .when()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON);
    private static RequestSpecification requestSpecificationAux;

    private static Response response;

    public static Response getResponse() {
        return response;
    }
    private static void setResponse(Response response) {
        RestSupport.response = response;
    }

    public static void clearRequestSpecification(){ requestSpecification = null; }
    private static RequestSpecification buildBaseRequestSpecification() {
        clearRequestSpecification();
        if (!(requestSpecificationAux == null)) {
            requestSpecification.headers(((RequestSpecificationImpl) requestSpecificationAux).getHeaders());
            requestSpecification.cookies(((RequestSpecificationImpl) requestSpecificationAux).getCookies());
        }

        requestSpecification = given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        return requestSpecification;
    }
    public static void addHeader(Header h) {
        if (h != null) {
            requestSpecificationAux.header(h);
        }
    }
    public static void addCookies(Map<String, String> c) {
        if (c != null) {
            requestSpecificationAux.cookies(c);
        }
    }
    public static void executeGet(String endpoint, Integer statusCode) {
        response = buildBaseRequestSpecification()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .extract().response();
        printLogStart("GET",endpoint, "");
        printLog("GET",response);
        setResponse(response);
    }
    public static Response executeGet(String endpoint) {
        response = buildBaseRequestSpecification()
                .get(endpoint)
                .then()
                .extract().response();
        printLogStart("GET", endpoint, "");
        printLog("GET", response);
        return response;
    }
    public static Response executePost(String endpoint, Integer statusCode, LazyMap json) {
        response = buildBaseRequestSpecification()
                .body(json)
                .post(endpoint)
                .then()
                .statusCode(statusCode)
                .extract().response();
        printLogStart("POST",endpoint, json.toString());
        printLog("POST",response);
        return response;
    }

    public static Response executePost(String endpoint, LazyMap json) {
        response = buildBaseRequestSpecification()
                .body(json)
                .post(endpoint)
                .then()
                .extract().response();
        printLogStart("POST",endpoint, json.toString());
        printLog("POST",response);
        return response;
    }
    public static Response executePut(String endpoint, LazyMap json) {
        response = buildBaseRequestSpecification()
                .body(json)
                .put(endpoint)
                .then()
                .extract().response();
        printLogStart("PUT",endpoint, json.toString());
        printLog("PUT",response);
        return response;
    }

    public static Response executeDelete(String endpoint) {
        response = buildBaseRequestSpecification()
                .delete(endpoint)
                .then()
                .extract().response();
        printLogStart("DELETE",endpoint, "");
        printLog("DELETE",response);
        return response;
    }

    public static Response executeOptions(String endpoint) {
        response = buildBaseRequestSpecification()
                .options(endpoint)
                .then()
                .extract().response();
        printLogStart("OPTIONS",endpoint, "");
        printLog("OPTIONS",response);
        return response;
    }

    public static Response executePatch(String endpoint, LazyMap json) {
        response = buildBaseRequestSpecification()
                .body(json)
                .patch(endpoint)
                .then()
                .extract().response();
        printLogStart("PATCH",endpoint, json.toString());
        printLog("PATCH",response);
        return response;
    }

    public static Integer getResponseCode() {
        return response.getStatusCode();
    }

    public static Object key(String field) {
        return response.getBody().jsonPath().get(field);
    }

    private static void printLogStart(String method, String url, String json){
        System.out.println("\n====================================\n");
        System.out.println("METHOD: [ "+ method + " (Request) ]");
        System.out.println("Endpoint: [ "+ url + " ]");
        System.out.println("Headers: [ "+ ((RequestSpecificationImpl) requestSpecification).getHeaders().toString() + " ]");
        System.out.println("Body - Request: [ " + json + " ]");

        Hooks.scenario.write("METHOD: [ "+ method + " (Request) ]");
        Hooks.scenario.write("Endpoint: [ "+ url + " ]");
        Hooks.scenario.write("Headers: [ "+ ((RequestSpecificationImpl) requestSpecification).getHeaders().toString() + " ]");
        Hooks.scenario.write("Body - Request: [ " + json + " ]");
    }

    private static void printLog(String method, Response response){
        System.out.println("\n------------------------------------\n");
        System.out.println("METHOD: [ "+ method + " (Response) ]");
        System.out.println("Status Code: [ "+ String.valueOf(response.statusCode()) + " ]");
        System.out.println("Response: [ "+ response.getBody().asString() + " ]");
        System.out.println("\n====================================");

        Hooks.scenario.write("------------------------------------");
        Hooks.scenario.write("METHOD: [ "+ method + " (Response) ]");
        Hooks.scenario.write("Status Code: [ "+ String.valueOf(response.statusCode()) + " ]");
        Hooks.scenario.write("Response: [ "+ response.getBody().asString() + " ]");
        Hooks.scenario.write("");
    }

}
