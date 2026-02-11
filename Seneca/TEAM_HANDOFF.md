# Team Handoff: Baseline + First 8 Tests

This file describes exactly what teammates should copy/use from this codebase.

## 1) Mandatory Shared Framework Files
Copy these first (required by both UI and API tests):

- `Seneca/pom.xml`
- `Seneca/src/test/resources/config.properties`
- `Seneca/src/test/resources/allure.properties`
- `Seneca/testng-ui.xml`
- `Seneca/testng-api.xml`
- `Seneca/testng-parallel.xml`

Core shared code:
- `Seneca/src/test/java/com/automationexercise/core/config/ConfigManager.java`
- `Seneca/src/test/java/com/automationexercise/core/driver/DriverManager.java`
- `Seneca/src/test/java/com/automationexercise/core/ui/WaitUtils.java`
- `Seneca/src/test/java/com/automationexercise/core/ui/BasePage.java`
- `Seneca/src/test/java/com/automationexercise/core/allure/AllureAttachmentUtils.java`
- `Seneca/src/test/java/com/automationexercise/core/listeners/UiFailureListener.java`
- `Seneca/src/test/java/com/automationexercise/core/data/TestDataFactory.java`
- `Seneca/src/test/java/com/automationexercise/core/model/UserData.java`

## 2) API Baseline + First 4 API Cases
Files for API infrastructure and cases 1-4:

- `Seneca/src/test/java/com/automationexercise/api/base/BaseApiTest.java`
- `Seneca/src/test/java/com/automationexercise/api/client/ApiClient.java`
- `Seneca/src/test/java/com/automationexercise/api/client/UserAccountApiHelper.java`
- `Seneca/src/test/java/com/automationexercise/api/tests/ProductsAndBrandsApiTest.java`

Coverage in `ProductsAndBrandsApiTest.java`:
- API 1: Get All Products List
- API 2: POST To All Products List
- API 3: Get All Brands List
- API 4: PUT To All Brands List

## 3) UI POM + First 4 UI Cases
Files for UI architecture and cases 1-4:

- `Seneca/src/test/java/com/automationexercise/ui/tests/BaseUiTest.java`
- `Seneca/src/test/java/com/automationexercise/ui/tests/AuthUiTest.java`
- `Seneca/src/test/java/com/automationexercise/ui/pages/HomePage.java`
- `Seneca/src/test/java/com/automationexercise/ui/pages/LoginPage.java`
- `Seneca/src/test/java/com/automationexercise/ui/pages/SignupPage.java`
- `Seneca/src/test/java/com/automationexercise/ui/pages/AccountCreatedPage.java`
- `Seneca/src/test/java/com/automationexercise/ui/pages/AccountDeletedPage.java`

Coverage in `AuthUiTest.java`:
- UI TC01: Register User
- UI TC02: Login User with correct email and password
- UI TC03: Login User with incorrect email and password
- UI TC04: Logout User

## 4) How Teammates Extend
When adding new test cases:

1. Reuse existing base classes and API/UI helpers.
2. Keep UI tests under `ui/tests` and page objects under `ui/pages`.
3. Keep API tests under `api/tests`; add endpoint helpers in `api/client` when needed.
4. Add `@Step` annotations for key actions/assertions.
5. Do not use hard waits (`Thread.sleep` is prohibited).

## 5) Commit Delegation Template
Use one case per commit:

- `feat(ui-tcXX): automate UI test case XX`
- `feat(api-XX): automate API case XX`

Recommended order:
1. Framework/setup commit
2. UI/API case commits individually
3. README ownership update commit

## 6) Local Validation Commands
From `Seneca/`:

```bash
mvn clean test -DsuiteXmlFile=testng-ui.xml
mvn clean test -DsuiteXmlFile=testng-api.xml
mvn clean test -DsuiteXmlFile=testng-parallel.xml
mvn allure:report
```
