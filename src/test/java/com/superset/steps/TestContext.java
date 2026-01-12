package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.restassured.response.Response;

public class TestContext {
    private SupersetApiClient client;
    private Response response;

    public SupersetApiClient getClient() {
        return client;
    }

    public void setClient(SupersetApiClient client) {
        this.client = client;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
