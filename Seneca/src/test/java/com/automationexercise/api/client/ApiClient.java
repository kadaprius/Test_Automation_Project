package com.automationexercise.api.client;

import com.automationexercise.core.config.ConfigManager;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class ApiClient {

    private final RequestSpecification baseSpec;

    public ApiClient() {
        this.baseSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("api.base.url"))
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Step("API GET {endpoint}")
    public Response get(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    @Step("API POST {endpoint} with form params")
    public Response postForm(String endpoint, Map<String, String> formParams) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    @Step("API POST {endpoint} without payload")
    public Response post(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    @Step("API PUT {endpoint}")
    public Response put(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    @Step("API DELETE {endpoint}")
    public Response delete(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    @Step("API DELETE {endpoint} with form params")
    public Response deleteForm(String endpoint, Map<String, String> formParams) {
        return io.restassured.RestAssured
                .given()
                .spec(baseSpec)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}