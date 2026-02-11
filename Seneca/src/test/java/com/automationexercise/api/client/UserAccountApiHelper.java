package com.automationexercise.api.client;

import com.automationexercise.core.model.UserData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;

public class UserAccountApiHelper {

    private final ApiClient apiClient;

    public UserAccountApiHelper(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Step("Create account through API for user: {user.email}")
    public Response createAccount(UserData user) {
        return apiClient.postForm("/createAccount", buildCreatePayload(user));
    }

    @Step("Delete account through API for user: {user.email}")
    public Response deleteAccount(UserData user) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", user.email());
        payload.put("password", user.password());
        return apiClient.deleteForm("/deleteAccount", payload);
    }

    @Step("Ensure account exists for user: {user.email}")
    public void ensureAccountExists(UserData user) {
        Response createResponse = createAccount(user);
        int responseCode = extractResponseCode(createResponse);

        if (responseCode == 201) {
            return;
        }

        String message = extractMessage(createResponse).toLowerCase();
        if (responseCode == 400 && message.contains("already exist")) {
            deleteAccount(user);
            Response retry = createAccount(user);
            Assert.assertEquals(extractResponseCode(retry), 201,
                "Unable to create account after deleting pre-existing user. Body: " + retry.asString());
            return;
        }

        Assert.fail("Unable to create user account. Response body: " + createResponse.asString());
    }

    @Step("Best-effort delete account for cleanup: {user.email}")
    public void deleteAccountSilently(UserData user) {
        try {
            deleteAccount(user);
        } catch (Exception ignored) {
            // Best effort cleanup. Cleanup failure should not hide test result.
        }
    }

    private Map<String, String> buildCreatePayload(UserData user) {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", user.name());
        payload.put("email", user.email());
        payload.put("password", user.password());
        payload.put("title", user.title());
        payload.put("birth_date", user.birthDate());
        payload.put("birth_month", normalizeBirthMonthForApi(user.birthMonth()));
        payload.put("birth_year", user.birthYear());
        payload.put("firstname", user.firstName());
        payload.put("lastname", user.lastName());
        payload.put("company", user.company());
        payload.put("address1", user.address1());
        payload.put("address2", user.address2());
        payload.put("country", user.country());
        payload.put("zipcode", user.zipcode());
        payload.put("state", user.state());
        payload.put("city", user.city());
        payload.put("mobile_number", user.mobileNumber());
        return payload;
    }

    private String normalizeBirthMonthForApi(String monthValue) {
        String normalized = monthValue == null ? "" : monthValue.trim().toLowerCase();

        return switch (normalized) {
            case "january" -> "1";
            case "february" -> "2";
            case "march" -> "3";
            case "april" -> "4";
            case "may" -> "5";
            case "june" -> "6";
            case "july" -> "7";
            case "august" -> "8";
            case "september" -> "9";
            case "october" -> "10";
            case "november" -> "11";
            case "december" -> "12";
            default -> monthValue;
        };
    }

    private int extractResponseCode(Response response) {
        try {
            return response.jsonPath().getInt("responseCode");
        } catch (Exception ex) {
            return -1;
        }
    }

    private String extractMessage(Response response) {
        try {
            String message = response.jsonPath().getString("message");
            return message == null ? "" : message;
        } catch (Exception ex) {
            return "";
        }
    }
}
