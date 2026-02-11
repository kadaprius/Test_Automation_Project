package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By LOGIN_HEADER = By.xpath("//h2[text()='Login to your account']");
    private static final By NEW_USER_SIGNUP_HEADER = By.xpath("//h2[text()='New User Signup!']");

    private static final By SIGNUP_NAME = By.cssSelector("input[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL = By.cssSelector("input[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.cssSelector("button[data-qa='signup-button']");

    private static final By LOGIN_EMAIL = By.cssSelector("input[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("input[data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[data-qa='login-button']");

    private static final By INCORRECT_LOGIN_ERROR = By.xpath("//p[text()='Your email or password is incorrect!']");
    private static final By EXISTING_EMAIL_ERROR = By.xpath("//p[text()='Email Address already exist!']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'Login to your account' is visible")
    public boolean isLoginToYourAccountVisible() {
        return isVisible(LOGIN_HEADER);
    }

    @Step("Verify 'New User Signup!' is visible")
    public boolean isNewUserSignupVisible() {
        return isVisible(NEW_USER_SIGNUP_HEADER);
    }

    @Step("Signup user with name and email")
    public SignupPage signupWithNameAndEmail(String name, String email) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, email);
        click(SIGNUP_BUTTON);
        return new SignupPage(driver);
    }

    @Step("Login with email and password")
    public HomePage login(String email, String password) {
        type(LOGIN_EMAIL, email);
        type(LOGIN_PASSWORD, password);
        click(LOGIN_BUTTON);
        return new HomePage(driver);
    }

    @Step("Verify incorrect login error message")
    public boolean isIncorrectLoginErrorVisible() {
        return isVisible(INCORRECT_LOGIN_ERROR);
    }

    @Step("Verify existing email error message")
    public boolean isExistingEmailErrorVisible() {
        return isVisible(EXISTING_EMAIL_ERROR);
    }
}
