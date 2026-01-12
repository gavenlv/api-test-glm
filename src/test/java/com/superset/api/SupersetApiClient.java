package com.superset.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class SupersetApiClient {
    private static final String BASE_URL = "http://localhost:8088";
    private String accessToken;
    private String refreshToken;

    public Response login(String username, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);
        credentials.put("provider", "db");
        credentials.put("refresh", "true");

        Response response = RestAssured.given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .body(credentials)
            .post("/api/v1/security/login");

        if (response.statusCode() == 200) {
            this.accessToken = response.jsonPath().getString("access_token");
            this.refreshToken = response.jsonPath().getString("refresh_token");
        }

        return response;
    }

    public Response get(String endpoint) {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("Authorization", "Bearer " + accessToken)
            .get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(body)
            .post(endpoint);
    }

    public Response put(String endpoint, Object body) {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(body)
            .put(endpoint);
    }

    public Response delete(String endpoint) {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("Authorization", "Bearer " + accessToken)
            .delete(endpoint);
    }

    public Response getHealth() {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .get("/health");
    }

    public Response getCsrfToken() {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .get("/api/v1/security/csrf_token/");
    }
}
