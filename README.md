###  UI Test Cases

- David Zoidze:
UI TC01: Verifies a new user can sign up, create an account, log in, and delete the account successfully.

UI TC02: Verifies an existing user can log in with valid credentials and then delete the account.

UI TC03: Verifies login fails with invalid credentials and shows the correct error message.

UI TC04: Verifies a logged-in user can log out and is redirected back to the login page.

- Giorgi Gogochishvili:
UI TC05: Verifies signup with an already registered email shows the “Email Address already exist!” message.

UI TC06: Verifies the Contact Us form can be submitted with file upload and shows the success message.

- Denis Apriamashvili:
UI TC07: Verifies clicking Test Cases from home navigates correctly to the Test Cases page.

UI TC08: Verifies the All Products page is visible, first product details open, and core product fields are shown.

- David Barsegyan:
UI TC09: Verifies product search shows the “Searched Products” section and relevant matching results.

UI TC10: Verifies home-page subscription works and shows “You have been successfully subscribed!” after submit.

### API Test Cases

- David Zoidze:
API 01: Verifies GET /productsList returns success and a non-empty products list.

API 02: Verifies POST /productsList is rejected as unsupported with responseCode 405.

API 03: Verifies GET /brandsList returns success and a non-empty brands list.

API 04: Verifies PUT /brandsList is rejected as unsupported with responseCode 405.

- Giorgi Gogochishvili:
API 05: Verifies POST /searchProduct with search_product returns matching product results.

API 06: Verifies POST /searchProduct without search_product returns the correct missing-parameter error.

- Denis Apriamashvili:
API 07: Verifies POST /verifyLogin with valid email/password returns “User exists!”.

API 08: Verifies POST /verifyLogin without email returns the expected bad-request validation error.

- David Barsegyan:
API 09: Verifies DELETE /verifyLogin is rejected as an unsupported request method.

API 10: Verifies POST /verifyLogin with invalid credentials returns “User not found!”.

## Presentation Script 

### 1) What We Implemented
We implemented a full end-to-end automation framework for Automation Exercise using Java, Selenium, TestNG, RestAssured, Maven, Allure, and Page Object Model.  
The scope includes 10 UI test cases and 10 API test cases from the official lists, with clear separation between UI and API code and support for TestNG suite-based parallel execution.

### 2) Framework Design Decisions And Why
1. We used Page Object Model for UI because it separates locators/actions from test logic, which improves maintainability when the UI changes.  
2. We split UI and API into separate packages because this keeps concerns isolated and makes team ownership and debugging easier.  
3. We used shared core utilities (driver management, waits, config, listeners, test data factory) to avoid duplicated code and enforce consistent behavior across tests.  
4. We used explicit waits instead of hard waits to reduce flaky tests and make runs faster and more stable.  
5. We used TestNG suite XML files for UI, API, and combined parallel runs so execution can be controlled cleanly in CI and local environments.

### 3) Reporting Decisions And Why
1. We integrated Allure with TestNG so every run produces structured test reports.  
2. We added `@Step` annotations in page objects, API client, and helpers so reports clearly show test flow and business actions.  
3. We added UI failure attachments (screenshot + page source) through a TestNG listener so failures have immediate visual and DOM evidence for debugging.  
4. We added API request/response attachments using `AllureRestAssured` filter so API failures can be diagnosed quickly without reproducing manually.

### 4) What We Validated Against Requirements
1. Mandatory stack is fully used: Java, Selenium, TestNG, RestAssured, Maven, Allure, POM.  
2. UI and API are clearly separated.  
3. Parallel execution is supported using TestNG suite configuration.  
4. Hard-coded waits are avoided; explicit waits are used.  
5. Allure report content includes steps, UI failure evidence, and API request/response details.


