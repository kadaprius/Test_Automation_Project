package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private static final By ALL_PRODUCTS_HEADER = By.xpath("//h2[text()='All Products']");
    private static final By SEARCHED_PRODUCTS_HEADER = By.xpath("//h2[text()='Searched Products']");
    private static final By SEARCH_INPUT = By.id("search_product");
    private static final By SEARCH_BUTTON = By.id("submit_search");
    private static final By PRODUCTS_LIST_ITEMS = By.cssSelector("div.features_items div.product-image-wrapper");
    private static final By FIRST_VIEW_PRODUCT_LINK = By.cssSelector("div.features_items div.choose a[href*='/product_details/']");
    private static final By PRODUCT_NAMES = By.cssSelector("div.features_items div.productinfo p");

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

    @Step("Search product by name: {searchTerm}")
    public ProductsPage searchProduct(String searchTerm) {
        type(SEARCH_INPUT, searchTerm);
        click(SEARCH_BUTTON);
        return this;
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public boolean isSearchedProductsVisible() {
        return isVisible(SEARCHED_PRODUCTS_HEADER);
    }

    @Step("Verify searched products list is visible and related to search term")
    public boolean hasResultsRelatedTo(String searchTerm) {
        List<WebElement> names = driver.findElements(PRODUCT_NAMES);
        if (names.isEmpty()) {
            return false;
        }

        String normalizedTerm = searchTerm.toLowerCase();
        return names.stream()
            .map(WebElement::getText)
            .filter(text -> text != null && !text.isBlank())
            .anyMatch(text -> text.toLowerCase().contains(normalizedTerm));
    }

    @Step("Click View Product for the first product")
    public ProductDetailsPage clickFirstViewProduct() {
        click(FIRST_VIEW_PRODUCT_LINK);
        return new ProductDetailsPage(driver);
    }
}
