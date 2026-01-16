package com.superset.steps;

import com.superset.api.SupersetApiClient;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TestDataManager {
    private SupersetApiClient client;
    private final Map<String, Object> testData;
    private final Map<String, Integer> createdIds;

    public TestDataManager(SupersetApiClient client) {
        this.client = client;
        this.testData = new HashMap<>();
        this.createdIds = new HashMap<>();
    }

    public void setClient(SupersetApiClient client) {
        this.client = client;
    }

    public void cleanup() {
        for (Map.Entry<String, Integer> entry : createdIds.entrySet()) {
            try {
                String type = entry.getKey();
                Integer id = entry.getValue();
                if (id != null) {
                    switch (type) {
                        case "theme":
                            client.delete("/api/v1/theme/" + id);
                            break;
                        case "tag":
                            client.delete("/api/v1/tag/" + id);
                            break;
                        case "saved_query":
                            client.delete("/api/v1/saved_query/" + id);
                            break;
                        case "dashboard":
                            client.delete("/api/v1/dashboard/" + id);
                            break;
                        case "chart":
                            client.delete("/api/v1/chart/" + id);
                            break;
                        case "database":
                            client.delete("/api/v1/database/" + id);
                            break;
                        case "annotation_layer":
                            client.delete("/api/v1/annotation_layer/" + id);
                            break;
                        case "css_template":
                            client.delete("/api/v1/css_template/" + id);
                            break;
                        case "report":
                            client.delete("/api/v1/report/" + id);
                            break;
                    }
                }
            } catch (Exception e) {
                System.err.println("Error cleaning up " + entry.getKey() + " with id " + entry.getValue() + ": " + e.getMessage());
            }
        }
        createdIds.clear();
        testData.clear();
    }

    public void put(String key, Object value) {
        testData.put(key, value);
    }

    public Object get(String key) {
        return testData.get(key);
    }

    public void setCreatedId(String type, Integer id) {
        createdIds.put(type, id);
    }

    public Integer getCreatedId(String type) {
        return createdIds.get(type);
    }

    public Response createTestTheme() {
        Map<String, Object> themeData = new HashMap<>();
        themeData.put("theme_name", "Test Theme " + System.currentTimeMillis());
        themeData.put("json_data", "{\"colors\": {\"primary\": \"#00A699\"}}");
        
        Response response = client.post("/api/v1/theme/", themeData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("theme", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing theme ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestTag() {
        Map<String, Object> tagData = new HashMap<>();
        tagData.put("name", "Test Tag " + System.currentTimeMillis());
        tagData.put("description", "Test tag description");
        
        Response response = client.post("/api/v1/tag/", tagData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("tag", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing tag ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestSavedQuery() {
        Map<String, Object> queryData = new HashMap<>();
        queryData.put("label", "Test Saved Query " + System.currentTimeMillis());
        queryData.put("description", "Test saved query description");
        queryData.put("schema", "public");
        queryData.put("catalog", "superset");
        queryData.put("sql", "SELECT * FROM test_users LIMIT 10");
        queryData.put("db_id", 1);
        
        Response response = client.post("/api/v1/saved_query/", queryData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("saved_query", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing saved query ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestDatabase() {
        Map<String, Object> dbData = new HashMap<>();
        dbData.put("database_name", "Test Database " + System.currentTimeMillis());
        dbData.put("sqlalchemy_uri", "sqlite:///test.db");
        dbData.put("expose_in_sqllab", true);
        
        Response response = client.post("/api/v1/database/", dbData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("database", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing database ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestAnnotationLayer() {
        Map<String, Object> layerData = new HashMap<>();
        layerData.put("name", "Test Annotation Layer " + System.currentTimeMillis());
        layerData.put("descr", "Test annotation layer description");
        
        Response response = client.post("/api/v1/annotation_layer/", layerData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("annotation_layer", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing annotation layer ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestCssTemplate() {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("template_name", "Test CSS Template " + System.currentTimeMillis());
        templateData.put("css", ".test { color: red; }");
        
        Response response = client.post("/api/v1/css_template/", templateData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("css_template", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing CSS template ID: " + e.getMessage());
            }
        }
        return response;
    }

    public Response createTestReport() {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("name", "Test Report " + System.currentTimeMillis());
        reportData.put("description", "Test report description");
        reportData.put("active", true);
        reportData.put("crontab", "0 0 * * *");
        reportData.put("database", 1);
        reportData.put("creation_method", "alerts_reports");
        reportData.put("report_format", "PNG");
        reportData.put("log_retention", 90);
        
        Map<String, Object> recipient = new HashMap<>();
        recipient.put("type", "Email");
        Map<String, Object> recipientConfig = new HashMap<>();
        recipientConfig.put("target", "admin@example.com");
        recipient.put("recipient_config_json", recipientConfig);
        
        reportData.put("recipients", new Object[]{recipient});
        
        Response response = client.post("/api/v1/report/", reportData);
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            try {
                Integer id = response.jsonPath().getInt("id");
                if (id != null) {
                    setCreatedId("report", id);
                }
            } catch (Exception e) {
                System.err.println("Error parsing report ID: " + e.getMessage());
            }
        }
        return response;
    }
}
