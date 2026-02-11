package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private static final By ALL_PRODUCTS_HEADER = By.xpath("//h2[text()='All Products']");
    private static final By PRODUCTS_LIST_ITEMS = By.cssSelector("div.features_items div.product-image-wrapper");
    private static final By FIRST_VIEW_PRODUCT_LINK = By.cssSelector("div.features_items div.choose a[href*='/product_details/']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify user is navigated to All Products page")
    public boolean isOnAllProductsPage() {
        return currentUrlContains("/products") && isVisible(ALL_PRODUCTS_HEADER);
    }

    @Step("Verify products list is visible")
    public boolean isProductsListVisible() {
        wait.visible(FIRST_VIEW_PRODUCT_LINK);
        List<WebElement> products = driver.findElements(PRODUCTS_LIST_ITEMS);
        return !products.isEmpty();
    }

    @Step("Click View Product for the first product")
    public ProductDetailsPage clickFirstViewProduct() {
        click(FIRST_VIEW_PRODUCT_LINK);
        return new ProductDetailsPage(driver);
    }
}
