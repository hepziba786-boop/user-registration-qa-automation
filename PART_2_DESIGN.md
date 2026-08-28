# PART 2: APPLICATION DESIGN

## Tech Stack
- **Backend:** Java 11+, Spring Boot 2.7.x, Spring Data JPA, Spring Security, Spring Web, Thymeleaf
- **Database:** H2 (in-memory for testing) or MySQL 8.0 (for production)
- **Build Tool:** Maven 3.6.x
- **Frontend:** HTML5, CSS3, Bootstrap 5 (for styling), JavaScript (vanilla for basic interaction)
- **Testing:** Selenium WebDriver 4.x, JUnit 5, WebDriverManager

## Data Model

### Entity Relationships

```
User (1) ──────→ (1) PersonalDetails
   ├── id (PK)
   ├── username (UNIQUE)
   ├── email (UNIQUE)
   └── passwordHash

PersonalDetails
   ├── id (PK)
   ├── userId (FK)
   ├── firstName
   ├── lastName
   ├── dateOfBirth
   └── gender

User (1) ──────→ (1) BankDetails
BankDetails
   ├── id (PK)
   ├── userId (FK)
   ├── accountNumber
   ├── accountType
   ├── IFSCCode
   └── bankName

User (1) ──────→ (1) AddressDetails
AddressDetails
   ├── id (PK)
   ├── userId (FK)
   ├── street
   ├── city
   ├── state
   ├── pincode
   └── country
```

## Field Names and HTML ID Mapping

### Registration Form (`/register`)

| Field Name | HTML ID | Type | Validation Rules |
|---|---|---|---|
| Username | `username` | Text Input | Required, 3-50 chars, alphanumeric + underscore |
| Email | `email` | Email Input | Required, valid email format |
| Password | `password` | Password Input | Required, minimum 8 characters |
| Confirm Password | `confirmPassword` | Password Input | Required, must match password |
| Register Button | `registerButton` | Button | N/A |
| Error Messages Container | `errorMessages` | Div | N/A |
| Success Message | `successMessage` | Div | N/A |

### Login Form (`/login`)

| Field Name | HTML ID | Type | Validation Rules |
|---|---|---|---|
| Username | `username` | Text Input | Required |
| Password | `password` | Password Input | Required |
| Login Button | `loginButton` | Button | N/A |
| Error Messages Container | `errorMessages` | Div | N/A |
| Remember Me | `rememberMe` | Checkbox | Optional |

### Personal Details Form (`/details/personal`)

| Field Name | HTML ID | Type | Validation Rules |
|---|---|---|---|
| First Name | `firstName` | Text Input | Required, 2-50 chars |
| Last Name | `lastName` | Text Input | Required, 2-50 chars |
| Date of Birth | `dateOfBirth` | Date Input | Required, DD/MM/YYYY format, not in future |
| Gender | `gender` | Select Dropdown | Required, values: Male, Female, Other |
| Save Button | `saveButton` | Button | N/A |
| Error Messages Container | `errorMessages` | Div | N/A |
| Success Message | `successMessage` | Div | N/A |

### Bank Details Form (`/details/bank`)

| Field Name | HTML ID | Type | Validation Rules |
|---|---|---|---|
| Account Number | `accountNumber` | Text Input | Required, 10-18 numeric digits |
| Account Type | `accountType` | Select Dropdown | Required, values: Savings, Current, BusinessSavings |
| IFSC Code | `IFSCCode` | Text Input | Required, 11 chars, format: AAAA0001234 |
| Bank Name | `bankName` | Text Input | Required, 2-100 chars |
| Save Button | `saveButton` | Button | N/A |
| Error Messages Container | `errorMessages` | Div | N/A |
| Success Message | `successMessage` | Div | N/A |

### Address Details Form (`/details/address`)

| Field Name | HTML ID | Type | Validation Rules |
|---|---|---|---|
| Street Address | `street` | Text Input | Required, 5-100 chars |
| City | `city` | Text Input | Required, 2-50 chars |
| State | `state` | Text Input | Required, 2-50 chars |
| Pincode | `pincode` | Text Input | Required, 5-6 numeric digits |
| Country | `country` | Text Input | Required, 2-50 chars |
| Save Button | `saveButton` | Button | N/A |
| Error Messages Container | `errorMessages` | Div | N/A |
| Success Message | `successMessage` | Div | N/A |

### Dashboard/View Data (`/dashboard`, `/view-data`)

| Element Name | HTML ID | Type |
|---|---|---|
| User Welcome Message | `welcomeMessage` | Div/Span |
| View Personal Details Link | `viewPersonalDetailsLink` | Link |
| View Bank Details Link | `viewBankDetailsLink` | Link |
| View Address Details Link | `viewAddressDetailsLink` | Link |
| Logout Button | `logoutButton` | Button |
| Dashboard Container | `dashboardContainer` | Div |
