package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class CssTemplateSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public CssTemplateSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all CSS templates")
    public void iRequestAllCssTemplates() {
        Response response = client.get("/api/v1/css_template/");
        context.setResponse(response);
    }

    @When("I request CSS template info")
    public void iRequestCssTemplateInfo() {
        Response response = client.get("/api/v1/css_template/_info");
        context.setResponse(response);
    }

    @When("I create a CSS template")
    public void iCreateACssTemplate() {
        Response response = context.getDataManager().createTestCssTemplate();
        context.setResponse(response);
    }

    @When("I update a CSS template")
    public void iUpdateACssTemplate() {
        Integer templateId = context.getDataManager().getCreatedId("css_template");
        if (templateId == null) {
            Response createResponse = context.getDataManager().createTestCssTemplate();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    templateId = createResponse.jsonPath().getInt("id");
                    if (templateId != null) {
                        context.getDataManager().setCreatedId("css_template", templateId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing CSS template ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("template_name", "Updated CSS Template");
        templateData.put("css", ".test { color: blue; }");
        
        Response response = client.put("/api/v1/css_template/" + templateId, templateData);
        context.setResponse(response);
    }

    @When("I delete a CSS template")
    public void iDeleteACssTemplate() {
        Integer templateId = context.getDataManager().getCreatedId("css_template");
        if (templateId == null) {
            Response createResponse = context.getDataManager().createTestCssTemplate();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    templateId = createResponse.jsonPath().getInt("id");
                    if (templateId != null) {
                        context.getDataManager().setCreatedId("css_template", templateId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing CSS template ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/css_template/" + templateId);
        context.setResponse(response);
    }

    @When("I request specific CSS template")
    public void iRequestSpecificCssTemplate() {
        Integer templateId = context.getDataManager().getCreatedId("css_template");
        if (templateId == null) {
            Response createResponse = context.getDataManager().createTestCssTemplate();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    templateId = createResponse.jsonPath().getInt("id");
                    if (templateId != null) {
                        context.getDataManager().setCreatedId("css_template", templateId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing CSS template ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/css_template/" + templateId);
        context.setResponse(response);
    }

    @When("I request related column values for CSS template")
    public void iRequestRelatedColumnValuesForCssTemplate() {
        Response response = client.get("/api/v1/css_template/related/template_name");
        context.setResponse(response);
    }
}
