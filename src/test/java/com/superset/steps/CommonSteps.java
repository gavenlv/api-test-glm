package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CommonSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        Response response = client.login("admin", "admin");
        context.setResponse(response);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Response response = context.getResponse();
        if (response.statusCode() != statusCode) {
            System.out.println("Expected status: " + statusCode + ", Actual: " + response.statusCode());
            System.out.println("Response body: " + response.getBody().asString());
        }
        assert response.statusCode() == statusCode : "Expected status " + statusCode + " but got " + response.statusCode();
    }

    @Then("the response status should be {int} or {int}")
    public void theResponseStatusShouldBeOr(int statusCode1, int statusCode2) {
        Response response = context.getResponse();
        int actualStatus = response.statusCode();
        assert actualStatus == statusCode1 || actualStatus == statusCode2 : 
            "Expected status " + statusCode1 + " or " + statusCode2 + " but got " + actualStatus;
    }

    @Then("the response should contain list")
    public void theResponseShouldContainList() {
        Response response = context.getResponse();
        assert response.jsonPath().get("result") != null || response.jsonPath().get("count") != null;
    }

    @Then("the response should contain report list")
    public void theResponseShouldContainReportList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain row level security list")
    public void theResponseShouldContainRowLevelSecurityList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain saved query list")
    public void theResponseShouldContainSavedQueryList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain group list")
    public void theResponseShouldContainGroupList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain permission list")
    public void theResponseShouldContainPermissionList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain resource list")
    public void theResponseShouldContainResourceList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain role list")
    public void theResponseShouldContainRoleList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain user list")
    public void theResponseShouldContainUserList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain query list")
    public void theResponseShouldContainQueryList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain tag list")
    public void theResponseShouldContainTagList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain theme list")
    public void theResponseShouldContainThemeList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain log list")
    public void theResponseShouldContainLogList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain menu items")
    public void theResponseShouldContainMenuItems() {
        theResponseShouldContainList();
    }

    @Then("the response should contain annotation layer list")
    public void theResponseShouldContainAnnotationLayerList() {
        theResponseShouldContainList();
    }

    @Then("the response should contain CSS template list")
    public void theResponseShouldContainCssTemplateList() {
        theResponseShouldContainList();
    }
}
