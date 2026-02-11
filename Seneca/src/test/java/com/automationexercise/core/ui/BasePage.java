package com.automationexercise.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    protected void click(By locator) {
        try {
            wait.click(locator);
        } catch (Exception ex) {
            WebElement element = wait.visible(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String value) {
        wait.type(locator, value);
    }

    protected void selectVisibleText(By locator, String text) {
        wait.selectByVisibleText(locator, text);
    }

    protected boolean isVisible(By locator) {
        try {
            wait.visible(locator);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected boolean currentUrlContains(String fragment) {
        return wait.urlContains(fragment);
    }
}
