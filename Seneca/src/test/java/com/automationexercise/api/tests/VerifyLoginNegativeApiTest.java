package com.automationexercise.api.tests;

import com.automationexercise.api.base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLoginNegativeApiTest extends BaseApiTest {

    @Test(description = "API 9: DELETE To Verify Login")
    @Description("Implements official API 9 from automationexercise.com/api_list")
    public void api09_deleteToVerifyLogin() {
        Response response = apiClient.delete("/verifyLogin");

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 405,
            "HTTP status can be 200 or 405 based on current service behavior.");
        assertJsonResponseCode(response, 405);
        Assert.assertEquals(response.jsonPath().getString("message"), "This request method is not supported.");
    }

    @Test(description = "API 10: POST To Verify Login with invalid details")
    @Description("Implements official API 10 from automationexercise.com/api_list")
    public void api10_postToVerifyLoginWithInvalidDetails() {
        String uniqueSuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String invalidEmail = "invalid.user." + uniqueSuffix + "@examplemail.com";

        Response response = apiClient.postForm("/verifyLogin", Map.of(
            "email", invalidEmail,
            "password", "WrongPass123"
        ));

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 404,
            "HTTP status can be 200 or 404 based on current service behavior.");
        assertJsonResponseCode(response, 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "User not found!");
    }
}
