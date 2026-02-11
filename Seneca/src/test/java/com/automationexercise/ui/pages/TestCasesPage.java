package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    private static final By TEST_CASES_HEADER = By.xpath("//h2[contains(@class,'title') and .//b[text()='Test Cases']]");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify user is navigated to Test Cases page")
    public boolean isOnTestCasesPage() {
        return currentUrlContains("/test_cases") && isVisible(TEST_CASES_HEADER);
    }
}
