package com.superset.steps;

import com.superset.api.SupersetApiClient;
import com.superset.steps.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class AnnotationLayerSteps {
    private final SupersetApiClient client;
    private final TestContext context;

    public AnnotationLayerSteps(TestContext context) {
        this.context = context;
        this.client = context.getClient();
    }

    @When("I request all annotation layers")
    public void iRequestAllAnnotationLayers() {
        Response response = client.get("/api/v1/annotation_layer/");
        context.setResponse(response);
    }

    @When("I request annotation layer info")
    public void iRequestAnnotationLayerInfo() {
        Response response = client.get("/api/v1/annotation_layer/_info");
        context.setResponse(response);
    }

    @When("I create an annotation layer")
    public void iCreateAnAnnotationLayer() {
        Response response = context.getDataManager().createTestAnnotationLayer();
        context.setResponse(response);
    }

    @When("I update an annotation layer")
    public void iUpdateAnAnnotationLayer() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> layerData = new HashMap<>();
        layerData.put("name", "Updated Annotation Layer");
        layerData.put("descr", "Updated annotation layer description");
        
        Response response = client.put("/api/v1/annotation_layer/" + layerId, layerData);
        context.setResponse(response);
    }

    @When("I delete an annotation layer")
    public void iDeleteAnAnnotationLayer() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/annotation_layer/" + layerId);
        context.setResponse(response);
    }

    @When("I request specific annotation layer")
    public void iRequestSpecificAnnotationLayer() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/annotation_layer/" + layerId);
        context.setResponse(response);
    }

    @When("I request related column values for annotation layer")
    public void iRequestRelatedColumnValuesForAnnotationLayer() {
        Response response = client.get("/api/v1/annotation_layer/related/name");
        context.setResponse(response);
    }

    @When("I request annotations for layer")
    public void iRequestAnnotationsForLayer() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/annotation_layer/" + layerId + "/annotation/");
        context.setResponse(response);
    }

    @When("I create an annotation")
    public void iCreateAnAnnotation() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> annotationData = new HashMap<>();
        annotationData.put("short_descr", "Test Annotation");
        annotationData.put("long_descr", "Test annotation description");
        annotationData.put("start_dttm", "2026-01-01T00:00:00");
        annotationData.put("end_dttm", "2026-01-02T00:00:00");
        
        Response response = client.post("/api/v1/annotation_layer/" + layerId + "/annotation/", annotationData);
        context.setResponse(response);
    }

    @When("I update an annotation")
    public void iUpdateAnAnnotation() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Map<String, Object> annotationData = new HashMap<>();
        annotationData.put("short_descr", "Updated Annotation");
        annotationData.put("long_descr", "Updated annotation description");
        
        Response response = client.put("/api/v1/annotation_layer/" + layerId + "/annotation/1", annotationData);
        context.setResponse(response);
    }

    @When("I delete an annotation")
    public void iDeleteAnAnnotation() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.delete("/api/v1/annotation_layer/" + layerId + "/annotation/1");
        context.setResponse(response);
    }

    @When("I request specific annotation")
    public void iRequestSpecificAnnotation() {
        Integer layerId = context.getDataManager().getCreatedId("annotation_layer");
        if (layerId == null) {
            Response createResponse = context.getDataManager().createTestAnnotationLayer();
            context.setResponse(createResponse);
            if (createResponse.statusCode() == 200 || createResponse.statusCode() == 201) {
                try {
                    layerId = createResponse.jsonPath().getInt("id");
                    if (layerId != null) {
                        context.getDataManager().setCreatedId("annotation_layer", layerId);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing annotation layer ID: " + e.getMessage());
                }
            }
            return;
        }
        
        Response response = client.get("/api/v1/annotation_layer/" + layerId + "/annotation/1");
        context.setResponse(response);
    }
}
