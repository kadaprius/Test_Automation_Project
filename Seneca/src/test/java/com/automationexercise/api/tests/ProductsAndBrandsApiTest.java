package com.automationexercise.api.tests;

import com.automationexercise.api.base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsAndBrandsApiTest extends BaseApiTest {

    @Test(description = "API 1: Get All Products List")
    @Description("Validate API 1 from Automation Exercise official API list.")
    public void api01_getAllProductsList() {
        Response response = apiClient.get("/productsList");

        Assert.assertEquals(response.statusCode(), 200, "HTTP status should be 200.");
        assertJsonResponseCode(response, 200);

        List<?> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products, "Products array should exist.");
        Assert.assertFalse(products.isEmpty(), "Products list should not be empty.");
    }

    @Test(description = "API 2: POST To All Products List")
    @Description("Validate API 2 using body-level responseCode because endpoint returns HTTP 200 with responseCode 405.")
    public void api02_postToAllProductsList() {
        Response response = apiClient.post("/productsList");

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 405,
            "HTTP status can be 200 or 405 based on current service behavior.");
        assertJsonResponseCode(response, 405);
        Assert.assertEquals(response.jsonPath().getString("message"), "This request method is not supported.");
    }

    @Test(description = "API 3: Get All Brands List")
    @Description("Validate API 3 from Automation Exercise official API list.")
    public void api03_getAllBrandsList() {
        Response response = apiClient.get("/brandsList");

        Assert.assertEquals(response.statusCode(), 200, "HTTP status should be 200.");
        assertJsonResponseCode(response, 200);

        List<?> brands = response.jsonPath().getList("brands");
        Assert.assertNotNull(brands, "Brands array should exist.");
        Assert.assertFalse(brands.isEmpty(), "Brands list should not be empty.");
    }

    @Test(description = "API 4: PUT To All Brands List")
    @Description("Validate API 4 using body-level responseCode because endpoint returns HTTP 200 with responseCode 405.")
    public void api04_putToAllBrandsList() {
        Response response = apiClient.put("/brandsList");

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 405,
            "HTTP status can be 200 or 405 based on current service behavior.");
        assertJsonResponseCode(response, 405);
        Assert.assertEquals(response.jsonPath().getString("message"), "This request method is not supported.");
    }
}
