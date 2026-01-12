package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class SecuritySteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public SecuritySteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all groups")
    public void iRequestAllGroups() {
        Response response = client.get("/api/v1/security/groups/");
        context.setResponse(response);
    }

    @When("I request group info")
    public void iRequestGroupInfo() {
        Response response = client.get("/api/v1/security/groups/_info");
        context.setResponse(response);
    }

    @When("I request specific group")
    public void iRequestSpecificGroup() {
        Response response = client.get("/api/v1/security/groups/1");
        context.setResponse(response);
    }

    @When("I request all permissions")
    public void iRequestAllPermissions() {
        Response response = client.get("/api/v1/security/permissions/");
        context.setResponse(response);
    }

    @When("I request permission info")
    public void iRequestPermissionInfo() {
        Response response = client.get("/api/v1/security/permissions/_info");
        context.setResponse(response);
    }

    @When("I request specific permission")
    public void iRequestSpecificPermission() {
        Response response = client.get("/api/v1/security/permissions/1");
        context.setResponse(response);
    }

    @When("I request all resources")
    public void iRequestAllResources() {
        Response response = client.get("/api/v1/security/resources/");
        context.setResponse(response);
    }

    @When("I request resource info")
    public void iRequestResourceInfo() {
        Response response = client.get("/api/v1/security/resources/_info");
        context.setResponse(response);
    }

    @When("I request specific resource")
    public void iRequestSpecificResource() {
        Response response = client.get("/api/v1/security/resources/1");
        context.setResponse(response);
    }

    @When("I request all roles")
    public void iRequestAllRoles() {
        Response response = client.get("/api/v1/security/roles/");
        context.setResponse(response);
    }

    @When("I request role info")
    public void iRequestRoleInfo() {
        Response response = client.get("/api/v1/security/roles/_info");
        context.setResponse(response);
    }

    @When("I search roles")
    public void iSearchRoles() {
        Response response = client.get("/api/v1/security/roles/search/");
        context.setResponse(response);
    }

    @When("I request specific role")
    public void iRequestSpecificRole() {
        Response response = client.get("/api/v1/security/roles/1");
        context.setResponse(response);
    }

    @When("I request role groups")
    public void iRequestRoleGroups() {
        Response response = client.get("/api/v1/security/roles/1/groups");
        context.setResponse(response);
    }

    @When("I request role permissions")
    public void iRequestRolePermissions() {
        Response response = client.get("/api/v1/security/roles/1/permissions/");
        context.setResponse(response);
    }

    @When("I request role users")
    public void iRequestRoleUsers() {
        Response response = client.get("/api/v1/security/roles/1/users");
        context.setResponse(response);
    }

    @When("I request all users")
    public void iRequestAllUsers() {
        Response response = client.get("/api/v1/security/users/");
        context.setResponse(response);
    }

    @When("I request user info")
    public void iRequestUserInfo() {
        Response response = client.get("/api/v1/security/users/_info");
        context.setResponse(response);
    }

    @When("I request specific user")
    public void iRequestSpecificUser() {
        Response response = client.get("/api/v1/security/users/1");
        context.setResponse(response);
    }

    @When("I request CSRF token")
    public void iRequestCSRFToken() {
        Response response = client.getCsrfToken();
        context.setResponse(response);
    }

    @When("I refresh token")
    public void iRefreshToken() {
        Response response = client.get("/api/v1/security/refresh");
        context.setResponse(response);
    }

    @When("I request guest token")
    public void iRequestGuestToken() {
        Response response = client.post("/api/v1/security/guest_token/", "{}");
        context.setResponse(response);
    }

    @When("I request available domains")
    public void iRequestAvailableDomains() {
        Response response = client.get("/api/v1/available_domains/");
        context.setResponse(response);
    }
}
