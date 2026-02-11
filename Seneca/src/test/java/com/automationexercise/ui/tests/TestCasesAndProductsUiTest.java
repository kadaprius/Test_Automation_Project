package com.automationexercise.ui.tests;

import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.ProductDetailsPage;
import com.automationexercise.ui.pages.ProductsPage;
import com.automationexercise.ui.pages.TestCasesPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCasesAndProductsUiTest extends BaseUiTest {

    @Test(description = "Test Case 7: Verify Test Cases Page")
    @Description("Implements official UI Test Case 7 from automationexercise.com/test_cases")
    public void tc07_verifyTestCasesPage() {
        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        TestCasesPage testCasesPage = homePage.clickTestCases();
        closeVignetteAd();
        Assert.assertTrue(testCasesPage.isOnTestCasesPage(),
            "User should be navigated to Test Cases page successfully.");
    }

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Description("Implements official UI Test Case 8 from automationexercise.com/test_cases")
    public void tc08_verifyAllProductsAndProductDetailPage() {
        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        ProductsPage productsPage = homePage.clickProducts();
        closeVignetteAd();
        Assert.assertTrue(productsPage.isOnAllProductsPage(),
            "User should be navigated to ALL PRODUCTS page.");
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list should be visible.");

        ProductDetailsPage productDetailsPage = productsPage.clickFirstViewProduct();
        closeVignetteAd();
        Assert.assertTrue(productDetailsPage.isOnProductDetailsPage(),
            "User should be landed on product detail page.");
        Assert.assertTrue(productDetailsPage.areCoreProductDetailsVisible(),
            "Product name, category, price, availability, condition, and brand should be visible.");
    }
}
