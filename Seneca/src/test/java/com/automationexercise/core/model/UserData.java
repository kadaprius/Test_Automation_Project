package com.automationexercise.core.model;

public record UserData(
    String name,
    String email,
    String password,
    String title,
    String birthDate,
    String birthMonth,
    String birthYear,
    String firstName,
    String lastName,
    String company,
    String address1,
    String address2,
    String country,
    String state,
    String city,
    String zipcode,
    String mobileNumber
) {
}
