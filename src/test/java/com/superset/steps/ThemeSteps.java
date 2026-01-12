package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ThemeSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public ThemeSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all themes")
    public void iRequestAllThemes() {
        Response response = client.get("/api/v1/theme/");
        context.setResponse(response);
    }

    @When("I request theme info")
    public void iRequestThemeInfo() {
        Response response = client.get("/api/v1/theme/_info");
        context.setResponse(response);
    }

    @When("I create a theme")
    public void iCreateATheme() {
        Map<String, Object> themeData = new HashMap<>();
        themeData.put("name", "Test Theme");
        themeData.put("description", "Test theme description");
        themeData.put("is_default", false);
        themeData.put("is_dark", false);
        
        Response response = client.post("/api/v1/theme/", themeData);
        context.setResponse(response);
    }

    @When("I update a theme")
    public void iUpdateATheme() {
        Map<String, Object> themeData = new HashMap<>();
        themeData.put("name", "Updated Theme");
        themeData.put("description", "Updated theme description");
        
        Response response = client.put("/api/v1/theme/1", themeData);
        context.setResponse(response);
    }

    @When("I delete a theme")
    public void iDeleteATheme() {
        Response response = client.delete("/api/v1/theme/1");
        context.setResponse(response);
    }

    @When("I request specific theme")
    public void iRequestSpecificTheme() {
        Response response = client.get("/api/v1/theme/1");
        context.setResponse(response);
    }

    @When("I set theme as system dark")
    public void iSetThemeAsSystemDark() {
        Response response = client.put("/api/v1/theme/1/set_system_dark", "{}");
        context.setResponse(response);
    }

    @When("I set theme as system default")
    public void iSetThemeAsSystemDefault() {
        Response response = client.put("/api/v1/theme/1/set_system_default", "{}");
        context.setResponse(response);
    }

    @When("I unset system dark theme")
    public void iUnsetSystemDarkTheme() {
        Response response = client.put("/api/v1/theme/unset_system_dark", "{}");
        context.setResponse(response);
    }

    @When("I unset system default theme")
    public void iUnsetSystemDefaultTheme() {
        Response response = client.put("/api/v1/theme/unset_system_default", "{}");
        context.setResponse(response);
    }

    @When("I export theme")
    public void iExportTheme() {
        Response response = client.get("/api/v1/theme/export/");
        context.setResponse(response);
    }

    @When("I import theme")
    public void iImportTheme() {
        Map<String, Object> themeData = new HashMap<>();
        themeData.put("name", "Imported Theme");
        
        Response response = client.post("/api/v1/theme/import/", themeData);
        context.setResponse(response);
    }
}
