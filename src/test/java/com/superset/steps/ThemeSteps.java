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
        Response response = context.getDataManager().createTestTheme();
        context.setResponse(response);
    }

    @When("I update a theme")
    public void iUpdateATheme() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> themeData = new HashMap<>();
        themeData.put("theme_name", "Updated Theme");
        themeData.put("json_data", "{\"colors\": {\"primary\": \"#FF6B6B\"}}");
        
        Response response = client.put("/api/v1/theme/" + themeId, themeData);
        context.setResponse(response);
    }

    @When("I delete a theme")
    public void iDeleteATheme() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/theme/" + themeId);
        context.setResponse(response);
    }

    @When("I request specific theme")
    public void iRequestSpecificTheme() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/theme/" + themeId);
        context.setResponse(response);
    }

    @When("I set theme as system dark")
    public void iSetThemeAsSystemDark() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.put("/api/v1/theme/" + themeId + "/set_system_dark", "{}");
        context.setResponse(response);
    }

    @When("I set theme as system default")
    public void iSetThemeAsSystemDefault() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.put("/api/v1/theme/" + themeId + "/set_system_default", "{}");
        context.setResponse(response);
    }

    @When("I unset system dark theme")
    public void iUnsetSystemDarkTheme() {
        Response response = client.delete("/api/v1/theme/unset_system_dark");
        context.setResponse(response);
    }

    @When("I unset system default theme")
    public void iUnsetSystemDefaultTheme() {
        Response response = client.delete("/api/v1/theme/unset_system_default");
        context.setResponse(response);
    }

    @When("I export theme")
    public void iExportTheme() {
        Integer themeId = context.getDataManager().getCreatedId("theme");
        if (themeId == null) {
            Response createResponse = context.getDataManager().createTestTheme();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    themeId = createResponse.jsonPath().getInt("id");
                    if (themeId != null) {
                        context.getDataManager().setCreatedId("theme", themeId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing theme ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("q", new Integer[]{themeId});
        
        Response response = client.get("/api/v1/theme/export/", params);
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
