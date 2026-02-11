package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private static final By ACCOUNT_DELETED_HEADER = By.cssSelector("h2[data-qa='account-deleted']");
    private static final By CONTINUE_BUTTON = By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ACCOUNT DELETED!' is visible")
    public boolean isAccountDeletedVisible() {
        return isVisible(ACCOUNT_DELETED_HEADER);
    }

    @Step("Click Continue after account deletion")
    public HomePage clickContinue() {
        click(CONTINUE_BUTTON);
        return new HomePage(driver);
    }
}
