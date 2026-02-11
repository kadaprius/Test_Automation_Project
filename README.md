# Final Group Project: End-to-End Test Automation

This repository contains an End-to-End Test Automation Framework for:
- UI: https://automationexercise.com
- API: https://automationexercise.com/api_list

Implementation path:
- `Seneca/`

## Automation Stack
- Java 17
- Maven
- Selenium WebDriver
- TestNG
- RestAssured
- Allure Reporting
- Page Object Model (POM)

## Group Members
Update this section with real names before submission:

1. Member 1 (this machine owner)
2. Member 2
3. Member 3
4. Member 4
5. Member 5

## Test Case Ownership Mapping
### Implemented in this codebase now (Member 1)
- UI TC01: Register User
- UI TC02: Login User with correct email and password
- UI TC03: Login User with incorrect email and password
- UI TC04: Logout User
- API 01: Get All Products List
- API 02: POST To All Products List
- API 03: Get All Brands List
- API 04: PUT To All Brands List

### Remaining assignments (fill before final submission)
- Member 2: UI TC__ , API __
- Member 3: UI TC__ , API __
- Member 4: UI TC__ , API __
- Member 5: UI TC__ , API __

## Project Structure
- `Seneca/src/test/java/com/automationexercise/ui` -> UI POM + UI tests
- `Seneca/src/test/java/com/automationexercise/api` -> API client + API tests
- `Seneca/src/test/java/com/automationexercise/core` -> shared framework utilities
- `Seneca/src/test/resources` -> config + Allure properties
- `Seneca/testng-ui.xml` -> UI suite
- `Seneca/testng-api.xml` -> API suite
- `Seneca/testng-parallel.xml` -> parallel UI+API suite

## Prerequisites
Install on the machine where you run tests (for example IntelliJ machine):

1. Java 17
2. Maven 3.9+
3. Google Chrome
4. Internet access
5. Allure CLI (optional but recommended for local report serving)

## Run Commands
From repository root:

```bash
cd Seneca
```

Run only UI tests:

```bash
mvn clean test -DsuiteXmlFile=testng-ui.xml
```

Run only API tests:

```bash
mvn clean test -DsuiteXmlFile=testng-api.xml
```

Run UI + API together (parallel by suite):

```bash
mvn clean test -DsuiteXmlFile=testng-parallel.xml
```

Generate Allure report:

```bash
mvn allure:report
```

Serve Allure report locally:

```bash
mvn allure:serve
```

## Allure Artifacts Included
- Test steps via `@Step`
- UI failure screenshots and page source via TestNG listener
- API request/response attachments via Allure RestAssured filter

## Configuration
Main config file:
- `Seneca/src/test/resources/config.properties`

Useful runtime overrides:

```bash
mvn clean test -DsuiteXmlFile=testng-ui.xml -Dheadless=true
mvn clean test -DsuiteXmlFile=testng-ui.xml -Dbrowser=chrome
```

## Team Workflow (Code Share)
1. Member 1 pushes/exports baseline framework and first 8 tests (UI 1-4 + API 1-4).
2. Other members copy from baseline and implement assigned cases only.
3. Every test case should be committed separately (recommended):
   - `feat(ui-tc05): automate UI test case 5`
   - `feat(api-05): automate API test case 5`
4. Update this README ownership table after each merge.

## Note
This machine did not have Java/Maven runtime installed at implementation time, so test execution must be validated on the target IntelliJ-enabled machine.
