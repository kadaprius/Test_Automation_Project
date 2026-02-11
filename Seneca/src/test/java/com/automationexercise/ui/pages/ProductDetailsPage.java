package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private static final By PRODUCT_INFO_SECTION = By.cssSelector("div.product-information");
    private static final By PRODUCT_NAME = By.cssSelector("div.product-information h2");
    private static final By PRODUCT_CATEGORY = By.xpath("//div[@class='product-information']/p[contains(.,'Category:')]");
    private static final By PRODUCT_PRICE = By.cssSelector("div.product-information span span");
    private static final By PRODUCT_AVAILABILITY = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Availability')]]");
    private static final By PRODUCT_CONDITION = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Condition')]]");
    private static final By PRODUCT_BRAND = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Brand')]]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify user is landed on product detail page")
    public boolean isOnProductDetailsPage() {
        return currentUrlContains("/product_details/") && isVisible(PRODUCT_INFO_SECTION);
    }

    @Step("Verify product name, category, price, availability, condition, and brand are visible")
    public boolean areCoreProductDetailsVisible() {
        return isVisible(PRODUCT_NAME)
            && isVisible(PRODUCT_CATEGORY)
            && isVisible(PRODUCT_PRICE)
            && isVisible(PRODUCT_AVAILABILITY)
            && isVisible(PRODUCT_CONDITION)
            && isVisible(PRODUCT_BRAND);
    }
}
