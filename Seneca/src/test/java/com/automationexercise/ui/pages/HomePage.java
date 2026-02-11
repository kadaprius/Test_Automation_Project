package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By HOME_BANNER = By.cssSelector("div.item.active");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");
    private static final By LOGGED_IN_AS = By.xpath("//a[contains(.,'Logged in as')]");
    private static final By DELETE_ACCOUNT_LINK = By.cssSelector("a[href='/delete_account']");
    private static final By LOGOUT_LINK = By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Automation Exercise home page")
    public HomePage open(String baseUrl) {
        driver.get(baseUrl);
        wait.waitForPageToLoadComplete();
        return this;
    }

    @Step("Verify home page is visible")
    public boolean isHomePageVisible() {
        return isVisible(HOME_BANNER);
    }

    @Step("Click Signup / Login")
    public LoginPage clickSignupLogin() {
        click(SIGNUP_LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Verify 'Logged in as username' is visible")
    public boolean isLoggedInAsVisible(String expectedNamePart) {
        if (!isVisible(LOGGED_IN_AS)) {
            return false;
        }
        String actualText = wait.visible(LOGGED_IN_AS).getText();
        return actualText.toLowerCase().contains(expectedNamePart.toLowerCase());
    }

    @Step("Click Delete Account")
    public AccountDeletedPage clickDeleteAccount() {
        click(DELETE_ACCOUNT_LINK);
        return new AccountDeletedPage(driver);
    }

    @Step("Click Logout")
    public LoginPage clickLogout() {
        click(LOGOUT_LINK);
        return new LoginPage(driver);
    }
}
