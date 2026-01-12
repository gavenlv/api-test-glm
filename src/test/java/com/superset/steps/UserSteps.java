package com.superset.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class UserSteps {
    private final TestContext context;

    public UserSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have admin access")
    public void iHaveAdminAccess() {
        context.setClient(new SupersetApiClient());
        Response loginResponse = context.getClient().login("admin", "admin");
        Assertions.assertEquals(200, loginResponse.statusCode());
    }

    @When("I request all users")
    public void iRequestAllUsers() {
        context.setResponse(context.getClient().get("/api/v1/users/"));
    }

    @When("I request current user info")
    public void iRequestCurrentUserInfo() {
        context.setResponse(context.getClient().get("/api/v1/me/"));
    }

    @When("I request roles")
    public void iRequestRoles() {
        context.setResponse(context.getClient().get("/api/v1/roles/"));
    }
}
