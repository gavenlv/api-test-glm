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
        Response response = client.get("/api/v1/query/distinct/label");
        context.setResponse(response);
    }

    @When("I estimate query")
    public void iEstimateQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("database_id", 1);
        queryData.put("sql", "SELECT * FROM birth_names LIMIT 10");
        queryData.put("schema", "main");
        
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
        Response response = client.post("/api/v1/query/stop", "{}");
        context.setResponse(response);
    }

    @When("I request updated queries")
    public void iRequestUpdatedQueries() {
        Response response = client.get("/api/v1/query/updated_since");
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
        sqlData.put("profiling", false);
        
        Response response = client.post("/api/v1/sqllab/format_sql/", sqlData);
        context.setResponse(response);
    }

    @When("I request SQL Lab permalink")
    public void iRequestSqlLabPermalink() {
        Response response = client.get("/api/v1/sqllab/permalink");
        context.setResponse(response);
    }

    @When("I request specific SQL Lab permalink")
    public void iRequestSpecificSqlLabPermalink() {
        Response response = client.get("/api/v1/sqllab/permalink/test_key");
        context.setResponse(response);
    }

    @When("I request query results")
    public void iRequestQueryResults() {
        Response response = client.get("/api/v1/sqllab/results/");
        context.setResponse(response);
    }
}
