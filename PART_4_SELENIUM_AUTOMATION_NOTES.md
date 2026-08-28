# PART 4: SELENIUM TEST AUTOMATION PROJECT

## Setup Instructions for Eclipse

### Prerequisites
- Eclipse IDE for Java Developers (2021-12 or later)
- Java 11 JDK installed and configured
- Maven installed and configured in Eclipse (m2e plugin)
- Git installed

### Step 1: Import Maven Project into Eclipse

1. Open Eclipse
2. Go to `File` → `Import`
3. Select `Maven` → `Existing Maven Projects`
4. Click `Next`
5. Navigate to the `user-registration-automation` directory
6. Click `Finish` - Eclipse will automatically download dependencies

### Step 2: Verify JDK Configuration

1. Right-click on project → `Properties`
2. Search for "Java Compiler"
3. Ensure "Compiler compliance level" is set to 11 or higher
4. Click `Apply and Close`

### Step 3: Update Project

1. Right-click on project → `Maven` → `Update Project`
2. Select the project and click `OK`
3. Eclipse will download all dependencies

### Step 4: Verify WebDriver Setup

The project uses WebDriverManager (Bonigarcia) which automatically downloads ChromeDriver.

1. Ensure Chrome browser is installed
2. No manual driver setup is required

## Running Tests

### Via Eclipse UI

1. Right-click on test class (e.g., `AccountCreationTest.java`)
2. Select `Run As` → `JUnit Test`
3. Results appear in the JUnit view

### Run Specific Test Method

1. Right-click on test method
2. Select `Run As` → `JUnit Test`

### Via Maven Command Line

Open terminal in project root:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AccountCreationTest

# Run specific test method
mvn test -Dtest=AccountCreationTest#test_S1_TC01_validRegistration

# Run with skip clean
mvn test -DskipClean=true
```

### Run as Test Suite

Tests are organized by user story:

```bash
# All Account Creation tests (User Story 1)
mvn test -Dtest=AccountCreationTest

# All Login and Details tests (User Story 2)
mvn test -Dtest=LoginAndAddDetailsTest

# All Logout and Re-login tests (User Story 3)
mvn test -Dtest=LogoutReloginViewDataTest

# All tests in order
mvn test
```

## Test Execution Flow

### Test Execution Order (Important for Story 3)

Tests in `LogoutReloginViewDataTest` depend on data created in earlier tests. Maven Surefire plugin runs tests in:
1. Alphabetical order by class name
2. Alphabetical order by test method name within each class

Order of execution:
1. `AccountCreationTest` (all tests)
2. `LoginAndAddDetailsTest` (all tests)
3. `LogoutReloginViewDataTest` (all tests)

This ensures data dependency is satisfied.

## Project Structure

```
user-registration-automation/
├── src/
│   └── test/
│       ├── java/com/userapp/automation/
│       │   ├── pages/
│       │   │   ├── RegisterPage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── PersonalDetailsPage.java
│       │   │   ├── BankDetailsPage.java
│       │   │   ├── AddressDetailsPage.java
│       │   │   └── DashboardPage.java
│       │   ├── tests/
│       │   │   ├── AccountCreationTest.java (13 tests)
│       │   │   ├── LoginAndAddDetailsTest.java (13 tests)
│       │   │   └── LogoutReloginViewDataTest.java (10 tests)
│       │   └── utils/
│       │       ├── BaseTest.java (WebDriver setup/teardown)
│       │       ├── ScreenshotUtil.java (Screenshot on failure)
│       │       ├── TestResultWatcher.java (Test result logging)
│       │       └── TestDataLoader.java (CSV data loading)
│       └── resources/
│           └── testdata/
│               └── testdata.csv
├── target/
│   ├── screenshots/ (Generated at runtime)
│   └── test-results/ (Surefire reports)
├── pom.xml
└── README.md
```

## Page Object Model (POM) Design

Each page class encapsulates:
- **Locators**: Element IDs and selectors (By objects)
- **Actions**: User interactions (click, type, select)
- **Assertions**: Methods to retrieve display values
- **Waits**: Explicit WebDriverWait with appropriate conditions

### Example Page Class Method

```java
public void enterUsername(String username) {
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    element.clear();
    element.sendKeys(username);
    logger.debug("Entered username: {}", username);
}
```

## Test Data Management

Test data is externalized in `src/test/resources/testdata/testdata.csv`:

```csv
TestCaseId,Username,Email,Password,ConfirmPassword,FirstName,LastName,DateOfBirth,Gender,AccountNumber,AccountType,IFSCCode,BankName,Street,City,State,Pincode,Country
S1_TC01,testuser1,testuser1@gmail.com,SecurePass123,SecurePass123,,,,,,,,,,,,,
...
```

**Load test data:**
```java
Map<String, String> testData = TestDataLoader.loadTestData("S1_TC01");
String username = testData.get("Username");
```

## Logging

SLF4J with Logback is configured. Logs appear in:
- Console output during test execution
- No log file by default (can be configured in `logback.xml`)

**Log Levels:**
- DEBUG: Detailed test steps and element interactions
- INFO: Test start, major actions, page navigation
- WARN: Warnings for missing elements (handled gracefully)
- ERROR: Test failures and exceptions

## Screenshots on Failure

Screenshots are automatically saved on test failure:
- Location: `target/screenshots/`
- Format: `testname_yyyy-MM-dd_HH-mm-ss-SSS.png`

**Manual screenshot:**
```java
String filePath = ScreenshotUtil.takeScreenshot(driver, "mytest");
```

## Explicit Waits

All element interactions use Explicit Waits (no `Thread.sleep()`):

```java
WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
```

**Wait Timeout:** 15 seconds (configured in `BaseTest`)

## Common Issues & Solutions

### Issue: "Chrome not found" error
**Solution:** WebDriverManager will automatically download the correct ChromeDriver version. Ensure Chrome is installed.

### Issue: Tests fail with "element not found"
**Solution:** Check:
1. Application is running on `http://localhost:8080`
2. Element IDs in HTML match locators in page classes
3. Increase wait timeout in `BaseTest.setUp()`

### Issue: Session/Login issues
**Solution:** Each `@BeforeEach` creates a fresh browser instance. Tests are isolated.

### Issue: Maven build fails
**Solution:** Run `mvn clean install` to clear cache and rebuild

## Continuous Integration

### GitHub Actions Example

```yaml
name: Run Selenium Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn test
      - name: Upload screenshots
        if: failure()
        uses: actions/upload-artifact@v2
        with:
          name: screenshots
          path: target/screenshots/
```

## Test Coverage Summary

**Total Test Cases: 36** (mapped to 30+ scenarios from Part 1)

- **Account Creation (User Story 1):** 13 tests
  - Positive cases: 1
  - Negative cases: 12 (validation, duplicates, format errors)

- **Login & Add Details (User Story 2):** 13 tests
  - Positive cases: 5 (personal, bank, address details)
  - Negative cases: 8 (empty fields, invalid formats)
  - Authentication checks: 3 (redirect tests)

- **Logout & Re-login (User Story 3):** 10 tests
  - Logout: 1
  - Session validation: 2
  - Data persistence: 4
  - Session security: 1
  - Browser navigation: 1

## Next Steps

1. Import project into Eclipse
2. Run `mvn clean install` to download dependencies
3. Start the Spring Boot application: `cd ../user-registration-app && mvn spring-boot:run`
4. Run tests: Right-click project → Run As → Maven test
5. View results in Eclipse JUnit view
6. Check screenshots in `target/screenshots/` if tests fail
