package com.automationexercise.ui.tests;

import com.automationexercise.core.data.TestDataFactory;
import com.automationexercise.core.model.UserData;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.ProductsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchAndSubscriptionUiTest extends BaseUiTest {

    @Test(description = "Test Case 9: Search Product")
    @Description("Implements official UI Test Case 9 from automationexercise.com/test_cases")
    public void tc09_searchProduct() {
        String searchTerm = "top";

        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        homePage.clickProducts();
        ProductsPage productsPage = homePage.clickProducts();
        closeVignetteAd();
        Assert.assertTrue(productsPage.isOnAllProductsPage(),
            "User should be navigated to ALL PRODUCTS page successfully.");

        productsPage.searchProduct(searchTerm);
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), "'SEARCHED PRODUCTS' should be visible.");
        Assert.assertTrue(productsPage.hasResultsRelatedTo(searchTerm),
            "Products related to the searched keyword should be visible.");
    }

    @Test(description = "Test Case 10: Verify Subscription in home page")
    @Description("Implements official UI Test Case 10 from automationexercise.com/test_cases")
    public void tc10_verifySubscriptionInHomePage() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc10");

        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        homePage.scrollToFooter();
        Assert.assertTrue(homePage.isSubscriptionVisible(), "Text 'SUBSCRIPTION' should be visible.");

        homePage.subscribeWithEmail(user.email());
        Assert.assertTrue(homePage.isSubscriptionSuccessMessageVisible(),
            "Success message should be visible after subscription.");
    }
}
