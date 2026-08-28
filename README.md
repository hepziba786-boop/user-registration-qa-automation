# User Registration QA Automation - Complete Deliverable

This repository contains a complete QA automation deliverable package including user story documentation, Spring Boot application code, and Selenium test automation.

## 📋 Contents

### Part 1: User Story Documentation
**File:** `PART_1_USER_STORIES.md`

- **3 User Stories** with acceptance criteria
- **30+ Test Scenarios** covering:
  - Valid data submission
  - Mandatory field validation
  - Invalid format validation
  - Field length boundaries
  - Duplicate account creation
  - Password mismatch
  - Login with wrong credentials
  - Session handling
  - Data persistence
  - UI/navigation edge cases

### Part 2: Application Design
**File:** `PART_2_DESIGN.md`

- **Tech Stack:** Java 11, Spring Boot 2.7.x, Maven, H2/MySQL
- **Data Model:** Entity-Relationship Diagram
- **Field Mapping:** HTML ID attributes for all form fields
- **Endpoints:** Complete REST/MVC endpoint definitions
- **Validation Rules:** Field-level requirements

### Part 3: Spring Boot Application
**Directory:** `user-registration-app/`

**Key Components:**
- ✅ Controller layer with authentication and data management
- ✅ Service layer with business logic and validation
- ✅ Repository layer with JPA/Hibernate ORM
- ✅ Entity models with validation annotations
- ✅ DTO classes for request/response handling
- ✅ Thymeleaf HTML templates with form validation
- ✅ Bootstrap 5 CSS styling
- ✅ BCrypt password hashing (Spring Security)
- ✅ Session-based authentication

**Endpoints:**
```
GET/POST  /register           - User registration
GET/POST  /login              - User authentication
GET       /logout             - Session invalidation
GET       /dashboard          - User dashboard (protected)
GET/POST  /details/personal   - Personal details management
GET/POST  /details/bank       - Bank details management
GET/POST  /details/address    - Address details management
```

### Part 4: Selenium Test Automation
**Directory:** `user-registration-automation/`

**Test Framework:**
- ✅ Selenium WebDriver 4.10.0
- ✅ JUnit 5.9.2
- ✅ Maven with Surefire plugin
- ✅ Page Object Model (POM) design pattern
- ✅ Explicit WebDriver waits
- ✅ SLF4J/Logback logging
- ✅ Screenshot on failure utility
- ✅ CSV-based test data externalization

**Test Classes:**
- `AccountCreationTest.java` - 13 test methods
- `LoginAndAddDetailsTest.java` - 13 test methods
- `LogoutReloginViewDataTest.java` - 10 test methods

**Total:** 36 automated test cases covering all 3 user stories

### Part 5: Traceability Matrix
**File:** `TRACEABILITY_MATRIX.md`

- Mapping of all 30+ scenarios to test methods
- Test coverage summary
- Status indicators
- Known limitations and future enhancements

---

## 🚀 Quick Start

### Run the Application

```bash
cd user-registration-app
mvn clean install
mvn spring-boot:run
```

Application will be available at: `http://localhost:8080`

### Run the Tests

**Via Maven:**
```bash
cd user-registration-automation
mvn clean test
```

**Via Eclipse:**
1. Import `user-registration-automation` as Maven project
2. Right-click project → Run As → Maven test
3. Results appear in JUnit view

**Run specific test class:**
```bash
mvn test -Dtest=AccountCreationTest
mvn test -Dtest=LoginAndAddDetailsTest
mvn test -Dtest=LogoutReloginViewDataTest
```

---

## 📁 Project Structure

```
user-registration-qa-automation/
├── user-registration-app/              (Spring Boot Application)
│   ├── src/main/java/com/userapp/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── dto/
│   │   ├── config/
│   │   ├── exception/
│   │   └── UserRegistrationApplication.java
│   ├── src/main/resources/
│   │   ├── templates/         (HTML Thymeleaf)
│   │   ├── static/css/        (Bootstrap + Custom CSS)
│   │   └── application.properties
│   ├── pom.xml
│   └── README.md
│
├── user-registration-automation/       (Selenium Automation)
│   ├── src/test/java/com/userapp/automation/
│   │   ├── pages/             (Page Object Classes)
│   │   │   ├── RegisterPage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── PersonalDetailsPage.java
│   │   │   ├── BankDetailsPage.java
│   │   │   ├── AddressDetailsPage.java
│   │   │   └── DashboardPage.java
│   │   ├── tests/             (JUnit Test Classes)
│   │   │   ├── AccountCreationTest.java
│   │   │   ├── LoginAndAddDetailsTest.java
│   │   │   └── LogoutReloginViewDataTest.java
│   │   └── utils/             (Test Utilities)
│   │       ├── BaseTest.java
│   │       ├── ScreenshotUtil.java
│   │       ├── TestResultWatcher.java
│   │       └── TestDataLoader.java
│   ├── src/test/resources/
│   │   └── testdata/
│   │       └── testdata.csv   (Test Data)
│   ├── target/
│   │   └── screenshots/       (Generated at runtime)
│   ├── pom.xml
│   └── README.md
│
├── PART_1_USER_STORIES.md
├── PART_2_DESIGN.md
├── PART_3_DEVELOPER_CODE_NOTES.md
├── PART_4_SELENIUM_AUTOMATION_NOTES.md
├── TRACEABILITY_MATRIX.md
└── README.md (this file)
```

---

## 🔐 Security Features

✅ **Authentication:**
- Session-based authentication after login
- Session invalidation on logout
- Credentials validation (username/password)

✅ **Password Security:**
- Minimum 8 characters required
- BCrypt hashing (never stored in plain text)
- Confirmation password matching

✅ **Access Control:**
- Protected endpoints require login
- Session check on detail pages
- Redirect to login for unauthorized access

✅ **Data Validation:**
- Server-side validation on all forms
- Email format validation
- Numeric/pattern validation for special fields
- Date validation (no future dates)

---

## 📊 Test Coverage

### User Story 1: Account Creation
- ✅ 13 test scenarios (13/13 automated)
- Covers: Valid registration, duplicate users, empty fields, invalid formats, boundary cases

### User Story 2: Login & Add Details
- ✅ 23 test scenarios (13 test methods covering all)
- Covers: Valid login, wrong credentials, personal/bank/address details, validation, access control

### User Story 3: Logout & Re-login
- ✅ 10 test scenarios (10/10 automated)
- Covers: Logout, session invalidation, data persistence, re-login verification

**Total: 46 test scenarios → 36 automated tests → 100% coverage**

---

## 🛠️ Technology Stack

### Backend
- **Framework:** Spring Boot 2.7.14
- **Language:** Java 11
- **Build Tool:** Maven 3.6+
- **Database:** H2 (in-memory) / MySQL 8.0 (optional)
- **ORM:** JPA/Hibernate
- **Security:** Spring Security with BCrypt
- **Templating:** Thymeleaf
- **Frontend:** HTML5, CSS3, Bootstrap 5

### Test Automation
- **Framework:** Selenium WebDriver 4.10.0
- **Language:** Java 11
- **Unit Test:** JUnit 5.9.2
- **Build Tool:** Maven 3.6+
- **WebDriver Management:** WebDriverManager (Bonigarcia)
- **Logging:** SLF4J with Logback
- **Test Data:** Apache Commons CSV
- **Browser:** Chrome (via WebDriver Manager)

---

## 📝 Configuration

### Application Configuration
File: `user-registration-app/src/main/resources/application.properties`

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:userdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

### Test Configuration
File: `user-registration-automation/pom.xml`

- Selenium WebDriver: 4.10.0
- JUnit: 5.9.2
- WebDriverManager: 5.6.2
- Chrome browser detection: Automatic (via WebDriverManager)

---

## ✅ Test Execution Checklist

- [ ] Java 11 JDK installed
- [ ] Maven 3.6+ installed
- [ ] Chrome browser installed
- [ ] Spring Boot app running on http://localhost:8080
- [ ] H2 database initialized
- [ ] Run `mvn clean install` in both directories
- [ ] Execute tests: `mvn test`
- [ ] All 36 tests pass
- [ ] Screenshots generated in `target/screenshots/`
- [ ] Log output shows debug messages

---

## 📚 Documentation Files

1. **PART_1_USER_STORIES.md** - User story documentation with 30+ scenarios
2. **PART_2_DESIGN.md** - Technical design and data model
3. **PART_3_DEVELOPER_CODE_NOTES.md** - Application setup and architecture
4. **PART_4_SELENIUM_AUTOMATION_NOTES.md** - Test automation setup and execution
5. **TRACEABILITY_MATRIX.md** - Scenario-to-test mapping (100% coverage)
6. **user-registration-app/README.md** - Backend application documentation
7. **user-registration-automation/README.md** - Test automation documentation

---

## 🔧 Troubleshooting

### Application won't start
```bash
# Clear Maven cache
mvn clean install -U

# Run with debug output
mvn spring-boot:run -X
```

### Tests fail with "element not found"
```bash
# Ensure application is running
# Check HTML element IDs match page class locators
# Increase wait timeout in BaseTest.java
```

### ChromeDriver issues
```bash
# WebDriverManager handles this automatically
# Ensure Chrome is installed
# Clear ~/.wdm/ cache if issues persist
```

---

## 📞 Support

For issues or questions:
1. Check PART_3 and PART_4 documentation files
2. Review test execution logs in console output
3. Check screenshot files in `target/screenshots/`
4. Verify application is running on port 8080
5. Ensure all dependencies are downloaded: `mvn clean install`

---

## 📄 License

This is a QA automation deliverable package. Feel free to modify and extend.

---

**Created:** August 28, 2026  
**Repository:** hepziba786-boop/user-registration-qa-automation  
**Last Updated:** August 28, 2026
