package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class SqlLabSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public SqlLabSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request SQL Lab queries")
    public void iRequestSqlLabQueries() {
        Response response = client.get("/api/v1/query/");
        context.setResponse(response);
    }

    @When("I request distinct values for SQL Lab column")
    public void iRequestDistinctValuesForSqlLabColumn() {
        Map<String, Object> q = new HashMap<>();
        q.put("page", 1);
        q.put("page_size", 100);
        
        Map<String, Object> params = new HashMap<>();
        params.put("q", q);
        
        Response response = client.get("/api/v1/query/distinct/label", params);
        context.setResponse(response);
    }

    @When("I estimate query")
    public void iEstimateQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("database_id", 1);
        queryData.put("sql", "SELECT * FROM birth_names LIMIT 10");
        queryData.put("schema", "public");
        queryData.put("catalog", "superset");
        
        Response response = client.post("/api/v1/sqllab/estimate/", queryData);
        context.setResponse(response);
    }

    @When("I execute query")
    public void iExecuteQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("database_id", 1);
        queryData.put("sql", "SELECT * FROM birth_names LIMIT 10");
        queryData.put("schema", "main");
        queryData.put("runAsync", false);
        
        Response response = client.post("/api/v1/sqllab/execute/", queryData);
        context.setResponse(response);
    }

    @When("I stop query")
    public void iStopQuery() {
        Map<String, Object> stopData = new HashMap<>();
        stopData.put("client_id", "test_client_id");
        
        Response response = client.post("/api/v1/query/stop", stopData);
        context.setResponse(response);
    }

    @When("I request updated queries")
    public void iRequestUpdatedQueries() {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> q = new HashMap<>();
        q.put("last_updated_ms", 0);
        params.put("q", q);
        
        Response response = client.get("/api/v1/query/updated_since", params);
        context.setResponse(response);
    }

    @When("I request specific query")
    public void iRequestSpecificQuery() {
        Response response = client.get("/api/v1/query/1");
        context.setResponse(response);
    }

    @When("I export query results")
    public void iExportQueryResults() {
        Response response = client.get("/api/v1/sqllab/export/test_client/");
        context.setResponse(response);
    }

    @When("I format SQL")
    public void iFormatSQL() {
        Map<String, Object> sqlData = new HashMap<>();
        sqlData.put("sql", "SELECT * FROM birth_names LIMIT 10");
        sqlData.put("engine", "sqlite");
        
        Response response = client.post("/api/v1/sqllab/format_sql/", sqlData);
        context.setResponse(response);
    }

    @When("I request SQL Lab permalink")
    public void iRequestSqlLabPermalink() {
        Map<String, Object> permalinkData = new HashMap<>();
        Map<String, Object> formData = new HashMap<>();
        formData.put("datasource", "1");
        permalinkData.put("formData", formData);
        
        Response response = client.post("/api/v1/sqllab/permalink", permalinkData);
        context.setResponse(response);
    }

    @When("I request specific SQL Lab permalink")
    public void iRequestSpecificSqlLabPermalink() {
        Response response = client.get("/api/v1/sqllab/permalink/test_key");
        context.setResponse(response);
    }

    @When("I request query results")
    public void iRequestQueryResults() {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> q = new HashMap<>();
        q.put("key", "test_query_key");
        params.put("q", q);
        
        Response response = client.get("/api/v1/sqllab/results/", params);
        context.setResponse(response);
    }
}
