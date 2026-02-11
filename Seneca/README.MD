UI Test Cases

David Zoidze 
UI TC01: Verifies a new user can sign up, create an account, log in, and delete the account successfully.
UI TC02: Verifies an existing user can log in with valid credentials and then delete the account.
UI TC03: Verifies login fails with invalid credentials and shows the correct error message.
UI TC04: Verifies a logged-in user can log out and is redirected back to the login page.
Giorgi Gogochishvili 
UI TC05: Verifies signup with an already registered email shows the “Email Address already exist!” message. 
UI TC06: Verifies the Contact Us form can be submitted with file upload and shows the success message.
Denis Apriamashvili  
UI TC07: Verifies clicking Test Cases from home navigates correctly to the Test Cases page.
UI TC08: Verifies the All Products page is visible, first product details open, and core product fields are shown.
David Barsegyan 
UI TC09: Verifies product search shows the “Searched Products” section and relevant matching results.
UI TC10: Verifies home-page subscription works and shows “You have been successfully subscribed!” after submit.

API Test Cases

David Zoidze
API 01: Verifies GET /productsList returns success and a non-empty products list.
API 02: Verifies POST /productsList is rejected as unsupported with responseCode 405.
API 03: Verifies GET /brandsList returns success and a non-empty brands list.
API 04: Verifies PUT /brandsList is rejected as unsupported with responseCode 405.
Giorgi Gogochishvili 
API 05: Verifies POST /searchProduct with search_product returns matching product results.
API 06: Verifies POST /searchProduct without search_product returns the correct missing-parameter error.
Denis Apriamashvili  
API 07: Verifies POST /verifyLogin with valid email/password returns “User exists!”.
API 08: Verifies POST /verifyLogin without email returns the expected bad-request validation error.
David Barsegyan  
API 09: Verifies DELETE /verifyLogin is rejected as an unsupported request method.
API 10: Verifies POST /verifyLogin with invalid credentials returns “User not found!”.