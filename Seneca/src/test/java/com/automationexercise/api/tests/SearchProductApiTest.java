package com.automationexercise.api.tests;

import com.automationexercise.api.base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductApiTest extends BaseApiTest {

    @Test(description = "API 5: POST To Search Product")
    @Description("Validate API 5 from Automation Exercise official API list.")
    public void api05_postToSearchProduct() {
        Response response = apiClient.postForm("/searchProduct", Map.of("search_product", "top"));

        Assert.assertEquals(response.statusCode(), 200, "HTTP status should be 200.");
        assertJsonResponseCode(response, 200);

        List<?> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products, "Products array should exist.");
        Assert.assertFalse(products.isEmpty(), "Search result should not be empty.");
    }

    @Test(description = "API 6: POST To Search Product without search_product parameter")
    @Description("Validate API 6 using body-level responseCode because endpoint may still respond with HTTP 200.")
    public void api06_postToSearchProductWithoutSearchParameter() {
        Response response = apiClient.post("/searchProduct");

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 400,
            "HTTP status can be 200 or 400 based on current service behavior.");
        assertJsonResponseCode(response, 400);
        Assert.assertEquals(response.jsonPath().getString("message"),
            "Bad request, search_product parameter is missing in POST request.");
    }
}
