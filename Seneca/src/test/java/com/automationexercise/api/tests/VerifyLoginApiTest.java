package com.automationexercise.api.tests;

import com.automationexercise.api.base.BaseApiTest;
import com.automationexercise.api.client.UserAccountApiHelper;
import com.automationexercise.core.data.TestDataFactory;
import com.automationexercise.core.model.UserData;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyLoginApiTest extends BaseApiTest {

    private UserAccountApiHelper userAccountApiHelper;

    @BeforeMethod(alwaysRun = true)
    public void setupUserAccountHelper() {
        userAccountApiHelper = new UserAccountApiHelper(apiClient);
    }

    @Test(description = "API 7: POST To Verify Login with valid details")
    @Description("Validate API 7 from Automation Exercise official API list.")
    public void api07_postToVerifyLoginWithValidDetails() {
        UserData user = TestDataFactory.buildUniqueUser("api_07");
        userAccountApiHelper.ensureAccountExists(user);

        try {
            Response response = apiClient.postForm("/verifyLogin", Map.of(
                    "email", user.email(),
                    "password", user.password()
            ));

            Assert.assertEquals(response.statusCode(), 200, "HTTP status should be 200.");
            assertJsonResponseCode(response, 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "User exists!");
        } finally {
            userAccountApiHelper.deleteAccountSilently(user);
        }
    }

    @Test(description = "API 8: POST To Verify Login without email parameter")
    @Description("Validate API 8 using body-level responseCode because endpoint may still respond with HTTP 200.")
    public void api08_postToVerifyLoginWithoutEmailParameter() {
        Response response = apiClient.postForm("/verifyLogin", Map.of("password", "anyPassword123"));

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 400,
                "HTTP status can be 200 or 400 based on current service behavior.");
        assertJsonResponseCode(response, 400);
        Assert.assertEquals(response.jsonPath().getString("message"),
                "Bad request, email or password parameter is missing in POST request.");
    }
}