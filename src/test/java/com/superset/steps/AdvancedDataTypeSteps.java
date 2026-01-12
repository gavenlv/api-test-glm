package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class AdvancedDataTypeSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public AdvancedDataTypeSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I convert advanced data type")
    public void iConvertAdvancedDataType() {
        Map<String, Object> conversionData = new HashMap<>();
        conversionData.put("sql_expression", "CAST(column AS VARCHAR)");
        conversionData.put("target_type", "VARCHAR");
        
        Response response = client.post("/api/v1/advanced_data_type/convert", conversionData);
        context.setResponse(response);
    }

    @When("I request advanced data type types")
    public void iRequestAdvancedDataTypeTypes() {
        Response response = client.get("/api/v1/advanced_data_type/types");
        context.setResponse(response);
    }
}
