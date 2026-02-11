package com.automationexercise.api.base;

import com.automationexercise.api.client.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected ApiClient apiClient;

    @BeforeClass(alwaysRun = true)
    public void setupApiClient() {
        apiClient = new ApiClient();
    }

    @Step("Validate JSON responseCode field")
    protected void assertJsonResponseCode(Response response, int expectedResponseCode) {
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), expectedResponseCode,
            "Unexpected JSON responseCode. Body: " + response.asString());
    }
}
