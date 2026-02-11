package com.automationexercise.ui.pages;

import com.automationexercise.core.model.UserData;
import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    private static final By ENTER_ACCOUNT_INFO_HEADER = By.xpath("//b[text()='Enter Account Information']");

    private static final By TITLE_MR = By.id("id_gender1");
    private static final By PASSWORD = By.id("password");
    private static final By DAYS = By.id("days");
    private static final By MONTHS = By.id("months");
    private static final By YEARS = By.id("years");
    private static final By NEWSLETTER = By.id("newsletter");
    private static final By SPECIAL_OFFERS = By.id("optin");

    private static final By FIRST_NAME = By.id("first_name");
    private static final By LAST_NAME = By.id("last_name");
    private static final By COMPANY = By.id("company");
    private static final By ADDRESS1 = By.id("address1");
    private static final By ADDRESS2 = By.id("address2");
    private static final By COUNTRY = By.id("country");
    private static final By STATE = By.id("state");
    private static final By CITY = By.id("city");
    private static final By ZIPCODE = By.id("zipcode");
    private static final By MOBILE_NUMBER = By.id("mobile_number");

    private static final By CREATE_ACCOUNT_BUTTON = By.cssSelector("button[data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ENTER ACCOUNT INFORMATION' is visible")
    public boolean isEnterAccountInformationVisible() {
        return isVisible(ENTER_ACCOUNT_INFO_HEADER);
    }

    @Step("Fill account information and create account")
    public AccountCreatedPage fillDetailsAndCreateAccount(UserData user) {
        click(TITLE_MR);
        type(PASSWORD, user.password());
        selectVisibleText(DAYS, user.birthDate());
        selectVisibleText(MONTHS, user.birthMonth());
        selectVisibleText(YEARS, user.birthYear());

        click(NEWSLETTER);
        click(SPECIAL_OFFERS);

        type(FIRST_NAME, user.firstName());
        type(LAST_NAME, user.lastName());
        type(COMPANY, user.company());
        type(ADDRESS1, user.address1());
        type(ADDRESS2, user.address2());
        selectVisibleText(COUNTRY, user.country());
        type(STATE, user.state());
        type(CITY, user.city());
        type(ZIPCODE, user.zipcode());
        type(MOBILE_NUMBER, user.mobileNumber());

        click(CREATE_ACCOUNT_BUTTON);
        return new AccountCreatedPage(driver);
    }
}
