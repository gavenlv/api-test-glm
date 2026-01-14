package com.superset.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {
    private final TestContext context;

    public TestHooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void setup() {
        context.getDataManager().cleanup();
    }

    @After(order = 1)
    public void cleanup() {
        context.getDataManager().cleanup();
    }
}
