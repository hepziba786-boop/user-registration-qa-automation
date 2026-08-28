# PART 1: USER STORY DOCUMENTATION

## User Story 1: Account Creation

**Title:** Account Creation for New Users

**Story:** As a new user, I want to create an account (username, email, password, confirm password) so that I can access the application.

**Priority:** High

**Acceptance Criteria:**
- **Given** I am on the registration page, **When** I enter valid username, email, password, and confirm password, **Then** my account should be created and I should be redirected to the login page with a success message.
- **Given** I am on the registration page, **When** I leave any mandatory field (username, email, password, confirm password) empty, **Then** the form should display a validation error for that field.
- **Given** I am on the registration page, **When** I enter an invalid email format, **Then** an email validation error should be displayed.
- **Given** I am on the registration page, **When** I enter a password with less than 8 characters, **Then** a password strength error should be displayed.

### User Story 1 - Scenario Table

| Scenario ID | Scenario Title | Preconditions | Test Steps (Action) | Expected Result | Priority | Positive/Negative |
|---|---|---|---|---|---|---|
| S1_TC01 | Valid registration with all correct data | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser1"<br>3. Enter email: "testuser1@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Account created successfully. Redirected to login page. Success message displayed: "Account created successfully" | High | Positive |
| S1_TC02 | Registration with username already exists | User "existinguser" already registered | 1. Navigate to /register<br>2. Enter username: "existinguser"<br>3. Enter email: "newemail@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Registration fails. Error message: "Username already exists" | High | Negative |
| S1_TC03 | Registration with email already exists | Email "existing@gmail.com" already registered | 1. Navigate to /register<br>2. Enter username: "newuser"<br>3. Enter email: "existing@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Registration fails. Error message: "Email already exists" | High | Negative |
| S1_TC04 | Registration with empty username | Fresh application state | 1. Navigate to /register<br>2. Leave username empty<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Username is required" | High | Negative |
| S1_TC05 | Registration with empty email | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Leave email empty<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Email is required" | High | Negative |
| S1_TC06 | Registration with empty password | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Enter email: "test@gmail.com"<br>4. Leave password empty<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Password is required" | High | Negative |
| S1_TC07 | Registration with empty confirm password | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Leave confirm password empty<br>6. Click Register button | Validation error: "Confirm password is required" | High | Negative |
| S1_TC08 | Registration with invalid email format | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Enter email: "invalidemail"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Invalid email format" | High | Negative |
| S1_TC09 | Registration with password less than 8 characters | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "Pass12"<br>5. Enter confirm password: "Pass12"<br>6. Click Register button | Validation error: "Password must be at least 8 characters" | High | Negative |
| S1_TC10 | Registration with mismatched confirm password | Fresh application state | 1. Navigate to /register<br>2. Enter username: "testuser"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass456"<br>6. Click Register button | Validation error: "Passwords do not match" | High | Negative |
| S1_TC11 | Registration with username containing special characters | Fresh application state | 1. Navigate to /register<br>2. Enter username: "test@user#123"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Username can only contain alphanumeric characters and underscores" | Medium | Negative |
| S1_TC12 | Registration with username exceeding max length (50 chars) | Fresh application state | 1. Navigate to /register<br>2. Enter username: "thisusernameistoolongandexceedsthemaximumallowedlength"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Username must not exceed 50 characters" | Medium | Negative |
| S1_TC13 | Registration with username less than 3 characters | Fresh application state | 1. Navigate to /register<br>2. Enter username: "ab"<br>3. Enter email: "test@gmail.com"<br>4. Enter password: "SecurePass123"<br>5. Enter confirm password: "SecurePass123"<br>6. Click Register button | Validation error: "Username must be at least 3 characters" | Medium | Negative |

---

## User Story 2: Login & Add Personal Details

**Title:** Login & Add Personal, Bank, and Address Details

**Story:** As a registered user, I want to log in with my account and then add my Personal details, Bank details, and Address details so that my profile is complete.

**Priority:** High

**Acceptance Criteria:**
- **Given** I am a registered user on the login page, **When** I enter correct username and password, **Then** I should be logged in and redirected to the dashboard.
- **Given** I am logged in on the dashboard, **When** I navigate to Personal Details and fill in all required fields (firstName, lastName, dateOfBirth, gender), **Then** the details should be saved and a success message displayed.
- **Given** I am logged in on the dashboard, **When** I navigate to Bank Details and fill in all required fields (accountNumber, accountType, IFSCCode, bankName), **Then** the details should be saved and a success message displayed.
- **Given** I am logged in on the dashboard, **When** I navigate to Address Details and fill in all required fields (street, city, state, pincode, country), **Then** the details should be saved and a success message displayed.

### User Story 2 - Scenario Table

| Scenario ID | Scenario Title | Preconditions | Test Steps (Action) | Expected Result | Priority | Positive/Negative |
|---|---|---|---|---|---|---|
| S2_TC01 | Valid login with correct credentials | User "testuser2" registered with password "SecurePass123" | 1. Navigate to /login<br>2. Enter username: "testuser2"<br>3. Enter password: "SecurePass123"<br>4. Click Login button | Login successful. Redirected to dashboard. User name displayed in header | High | Positive |
| S2_TC02 | Login with wrong password | User "testuser2" exists | 1. Navigate to /login<br>2. Enter username: "testuser2"<br>3. Enter password: "WrongPass123"<br>4. Click Login button | Login fails. Error message: "Invalid username or password" | High | Negative |
| S2_TC03 | Login with non-existent username | Fresh application state | 1. Navigate to /login<br>2. Enter username: "nonexistentuser"<br>3. Enter password: "SecurePass123"<br>4. Click Login button | Login fails. Error message: "Invalid username or password" | High | Negative |
| S2_TC04 | Login with empty username | Fresh application state | 1. Navigate to /login<br>2. Leave username empty<br>3. Enter password: "SecurePass123"<br>4. Click Login button | Validation error: "Username is required" | High | Negative |
| S2_TC05 | Login with empty password | Fresh application state | 1. Navigate to /login<br>2. Enter username: "testuser2"<br>3. Leave password empty<br>4. Click Login button | Validation error: "Password is required" | High | Negative |
| S2_TC06 | Add valid personal details | User logged in, on Personal Details page | 1. Navigate to /details/personal<br>2. Enter firstName: "John"<br>3. Enter lastName: "Doe"<br>4. Enter dateOfBirth: "01/01/1990"<br>5. Select gender: "Male"<br>6. Click Save button | Details saved successfully. Success message: "Personal details saved". Data persisted in database | High | Positive |
| S2_TC07 | Add personal details with empty firstName | User logged in, on Personal Details page | 1. Navigate to /details/personal<br>2. Leave firstName empty<br>3. Enter lastName: "Doe"<br>4. Enter dateOfBirth: "01/01/1990"<br>5. Select gender: "Male"<br>6. Click Save button | Validation error: "First name is required" | High | Negative |
| S2_TC08 | Add personal details with empty lastName | User logged in, on Personal Details page | 1. Navigate to /details/personal<br>2. Enter firstName: "John"<br>3. Leave lastName empty<br>4. Enter dateOfBirth: "01/01/1990"<br>5. Select gender: "Male"<br>6. Click Save button | Validation error: "Last name is required" | High | Negative |
| S2_TC09 | Add personal details with invalid date (future date) | User logged in, on Personal Details page | 1. Navigate to /details/personal<br>2. Enter firstName: "John"<br>3. Enter lastName: "Doe"<br>4. Enter dateOfBirth: "01/01/2030"<br>5. Select gender: "Male"<br>6. Click Save button | Validation error: "Date of birth cannot be in the future" | High | Negative |
| S2_TC10 | Add personal details with invalid date format | User logged in, on Personal Details page | 1. Navigate to /details/personal<br>2. Enter firstName: "John"<br>3. Enter lastName: "Doe"<br>4. Enter dateOfBirth: "invalid-date"<br>5. Select gender: "Male"<br>6. Click Save button | Validation error: "Invalid date format. Use DD/MM/YYYY" | High | Negative |
| S2_TC11 | Add valid bank details | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Enter accountNumber: "1234567890123456"<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "SBIN0001234"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Details saved successfully. Success message: "Bank details saved" | High | Positive |
| S2_TC12 | Add bank details with empty accountNumber | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Leave accountNumber empty<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "SBIN0001234"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Validation error: "Account number is required" | High | Negative |
| S2_TC13 | Add bank details with non-numeric account number | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Enter accountNumber: "ABCD1234EFGH5678"<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "SBIN0001234"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Validation error: "Account number must contain only digits" | High | Negative |
| S2_TC14 | Add bank details with invalid IFSC code | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Enter accountNumber: "1234567890123456"<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "INVALID"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Validation error: "IFSC code must be in format: AAAA0001234 (11 characters)" | High | Negative |
| S2_TC15 | Add bank details with account number too short | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Enter accountNumber: "123456"<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "SBIN0001234"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Validation error: "Account number must be between 10 and 18 digits" | High | Negative |
| S2_TC16 | Add bank details with account number too long | User logged in, on Bank Details page | 1. Navigate to /details/bank<br>2. Enter accountNumber: "123456789012345678901"<br>3. Select accountType: "Savings"<br>4. Enter IFSCCode: "SBIN0001234"<br>5. Enter bankName: "State Bank of India"<br>6. Click Save button | Validation error: "Account number must be between 10 and 18 digits" | High | Negative |
| S2_TC17 | Add valid address details | User logged in, on Address Details page | 1. Navigate to /details/address<br>2. Enter street: "123 Main Street"<br>3. Enter city: "New York"<br>4. Enter state: "NY"<br>5. Enter pincode: "10001"<br>6. Enter country: "USA"<br>7. Click Save button | Details saved successfully. Success message: "Address details saved" | High | Positive |
| S2_TC18 | Add address details with empty street | User logged in, on Address Details page | 1. Navigate to /details/address<br>2. Leave street empty<br>3. Enter city: "New York"<br>4. Enter state: "NY"<br>5. Enter pincode: "10001"<br>6. Enter country: "USA"<br>7. Click Save button | Validation error: "Street address is required" | High | Negative |
| S2_TC19 | Add address details with non-numeric pincode | User logged in, on Address Details page | 1. Navigate to /details/address<br>2. Enter street: "123 Main Street"<br>3. Enter city: "New York"<br>4. Enter state: "NY"<br>5. Enter pincode: "ABCDE"<br>6. Enter country: "USA"<br>7. Click Save button | Validation error: "Pincode must contain only digits" | High | Negative |
| S2_TC20 | Add address details with invalid pincode length | User logged in, on Address Details page | 1. Navigate to /details/address<br>2. Enter street: "123 Main Street"<br>3. Enter city: "New York"<br>4. Enter state: "NY"<br>5. Enter pincode: "100"<br>6. Enter country: "USA"<br>7. Click Save button | Validation error: "Pincode must be 5-6 digits" | High | Negative |
| S2_TC21 | Access personal details page without login | Fresh browser state | 1. Directly navigate to /details/personal<br>2. Attempt to view the page | Redirected to login page. Message: "Please log in to continue" | High | Negative |
| S2_TC22 | Access bank details page without login | Fresh browser state | 1. Directly navigate to /details/bank<br>2. Attempt to view the page | Redirected to login page. Message: "Please log in to continue" | High | Negative |
| S2_TC23 | Access address details page without login | Fresh browser state | 1. Directly navigate to /details/address<br>2. Attempt to view the page | Redirected to login page. Message: "Please log in to continue" | High | Negative |

---

## User Story 3: Logout, Re-login, and View Data

**Title:** Logout, Re-login, and Verify Data Persistence

**Story:** As a registered user, I want to log out, log back in, and view the personal, bank, and address details I previously saved so that I can confirm my data was stored correctly.

**Priority:** High

**Acceptance Criteria:**
- **Given** I am logged in, **When** I click the Logout button, **Then** I should be logged out and redirected to the login page, and my session should be invalidated.
- **Given** I have logged out and previously saved personal, bank, and address details, **When** I log back in, **Then** I should see the dashboard with an option to view my saved details.
- **Given** I am logged in after re-login, **When** I navigate to view my personal, bank, and address details, **Then** all data I saved previously should be displayed correctly.
- **Given** I am on the dashboard after logout/re-login, **When** I use the browser back button, **Then** I should not be able to access the previous page or details without re-logging in.

### User Story 3 - Scenario Table

| Scenario ID | Scenario Title | Preconditions | Test Steps (Action) | Expected Result | Priority | Positive/Negative |
|---|---|---|---|---|---|---|
| S3_TC01 | Successful logout | User logged in, on dashboard | 1. Click Logout button<br>2. Verify navigation | Logged out successfully. Redirected to login page. Session invalidated | High | Positive |
| S3_TC02 | Accessing dashboard after logout | User just logged out | 1. Directly navigate to /dashboard<br>2. Attempt to view the page | Redirected to login page. Message: "Session expired. Please log in again" | High | Negative |
| S3_TC03 | Accessing details pages after logout | User just logged out | 1. Directly navigate to /details/personal<br>2. Attempt to view the page | Redirected to login page. Message: "Please log in to continue" | High | Negative |
| S3_TC04 | Login after logout and view personal details | User has logged out. Personal details saved previously | 1. Navigate to /login<br>2. Enter username and password<br>3. Click Login button<br>4. Navigate to /details/personal | Login successful. Redirected to dashboard. Personal details displayed correctly with previously saved data | High | Positive |
| S3_TC05 | Login after logout and view bank details | User has logged out. Bank details saved previously | 1. Navigate to /login<br>2. Enter username and password<br>3. Click Login button<br>4. Navigate to /details/bank | Login successful. Redirected to dashboard. Bank details displayed correctly with previously saved data | High | Positive |
| S3_TC06 | Login after logout and view address details | User has logged out. Address details saved previously | 1. Navigate to /login<br>2. Enter username and password<br>3. Click Login button<br>4. Navigate to /details/address | Login successful. Redirected to dashboard. Address details displayed correctly with previously saved data | High | Positive |
| S3_TC07 | Verify personal details data persistence | User logged in, personal details saved | 1. From dashboard, navigate to /details/personal<br>2. Verify all fields display saved data<br>3. Logout<br>4. Login again<br>5. Navigate to /details/personal | All personal details (firstName, lastName, dateOfBirth, gender) are displayed correctly before and after logout/re-login. Data matches saved values | High | Positive |
| S3_TC08 | Verify bank details data persistence | User logged in, bank details saved | 1. From dashboard, navigate to /details/bank<br>2. Verify all fields display saved data<br>3. Logout<br>4. Login again<br>5. Navigate to /details/bank | All bank details (accountNumber, accountType, IFSCCode, bankName) are displayed correctly before and after logout/re-login. Data matches saved values | High | Positive |
| S3_TC09 | Verify address details data persistence | User logged in, address details saved | 1. From dashboard, navigate to /details/address<br>2. Verify all fields display saved data<br>3. Logout<br>4. Login again<br>5. Navigate to /details/address | All address details (street, city, state, pincode, country) are displayed correctly before and after logout/re-login. Data matches saved values | High | Positive |
| S3_TC10 | Browser back button after logout | User has logged out from dashboard | 1. Click Logout button<br>2. Use browser back button<br>3. Attempt to navigate to previous page | Back button does not grant access to dashboard. Redirected to login page or shows an empty/expired page | High | Negative |
