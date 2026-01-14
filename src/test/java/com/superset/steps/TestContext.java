package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.restassured.response.Response;

public class TestContext {
    private SupersetApiClient client;
    private Response response;
    private TestDataManager dataManager;

    public TestContext() {
    }

    public SupersetApiClient getClient() {
        return client;
    }

    public void setClient(SupersetApiClient client) {
        this.client = client;
        if (this.dataManager == null) {
            this.dataManager = new TestDataManager(client);
        } else {
            this.dataManager.setClient(client);
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public TestDataManager getDataManager() {
        if (this.dataManager == null) {
            this.dataManager = new TestDataManager(client);
        }
        return dataManager;
    }
}
