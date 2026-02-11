package com.automationexercise.core.data;

import com.automationexercise.core.model.UserData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestDataFactory {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private TestDataFactory() {
    }

    public static UserData buildUniqueUser(String prefix) {
        String stamp = LocalDateTime.now().format(FORMATTER);
        String normalizedPrefix = prefix.toLowerCase().replaceAll("[^a-z0-9]", "");

        String name = "AE " + prefix + " User";
        String email = normalizedPrefix + "." + stamp + "@examplemail.com";
        String password = "AePass" + stamp.substring(stamp.length() - 6);

        return new UserData(
            name,
            email,
            password,
            "Mr",
            "1",
            "January",
            "1992",
            "AEFirst" + stamp.substring(stamp.length() - 4),
            "AELast" + stamp.substring(stamp.length() - 4),
            "QA Corp",
            "221B Baker Street",
            "Suite 10",
            "India",
            "Maharashtra",
            "Mumbai",
            "400001",
            "999999" + stamp.substring(stamp.length() - 4)
        );
    }
}
