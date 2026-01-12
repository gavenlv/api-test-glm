package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class AuthenticationSteps {
    private final TestContext context;

    public AuthenticationSteps(TestContext context) {
        this.context = context;
    }

    @Given("Superset API client is initialized")
    public void supersetApiClientIsInitialized() {
        context.setClient(new SupersetApiClient());
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        context.setResponse(context.getClient().login(username, password));
    }

    @Then("the login should be successful")
    public void theLoginShouldBeSuccessful() {
        Assertions.assertEquals(200, context.getResponse().statusCode());
        Assertions.assertNotNull(context.getResponse().jsonPath().getString("access_token"));
    }
}
