package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class DatabaseSteps {
    private final TestContext context;

    public DatabaseSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have admin privileges")
    public void iHaveAdminPrivileges() {
        context.setClient(new SupersetApiClient());
        Response loginResponse = context.getClient().login("admin", "admin");
        Assertions.assertEquals(200, loginResponse.statusCode());
    }

    @When("I request all databases")
    public void iRequestAllDatabases() {
        context.setResponse(context.getClient().get("/api/v1/database/"));
    }

    @When("I request database with id {int}")
    public void iRequestDatabaseWithId(int id) {
        context.setResponse(context.getClient().get("/api/v1/database/" + id));
    }

    @Then("the response should contain database list")
    public void theResponseShouldContainDatabaseList() {
        Assertions.assertNotNull(context.getResponse().jsonPath().getList("result"));
    }
}
