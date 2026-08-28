# PART 3: DEVELOPER CODE - SPRING BOOT APPLICATION

## Running the Application Locally

### Prerequisites
- Java 11 or higher installed
- Maven 3.6.x or higher
- MySQL 8.0 (optional - application uses H2 in-memory database by default)

### Steps to Run

1. Navigate to the application directory:
   ```bash
   cd user-registration-app
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start on `http://localhost:8080`

### Default Configuration
- **Database**: H2 in-memory database
- **Database URL**: `jdbc:h2:mem:userdb`
- **Username**: `sa`
- **Password**: (empty)
- **H2 Console**: Available at `http://localhost:8080/h2-console`

### Key Features Implemented

#### Authentication
- User registration with validation
- Secure password hashing using BCrypt
- Login with session management
- Logout with session invalidation

#### Data Management
- Personal Details (First Name, Last Name, Date of Birth, Gender)
- Bank Details (Account Number, Account Type, IFSC Code, Bank Name)
- Address Details (Street, City, State, Pincode, Country)

#### Validation
- Server-side validation on all forms
- Email format validation
- Password strength requirements (minimum 8 characters)
- Numeric validation for account numbers and pincodes
- Pattern validation for IFSC codes (11 characters, format: AAAA0001234)
- Date validation (cannot be in future)

#### Security
- Session-based authentication
- Protected endpoints requiring login
- Password hashing with BCrypt
- CSRF token support (Spring Security default)

## Project Structure

```
user-registration-app/
├── src/
│   ├── main/
│   │   ├── java/com/userapp/
│   │   │   ├── UserRegistrationApplication.java (Entry point)
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── DetailsController.java
│   │   │   │   └── DashboardController.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── PersonalDetails.java
│   │   │   │   ├── BankDetails.java
│   │   │   │   └── AddressDetails.java
│   │   │   ├── dto/
│   │   │   │   ├── UserRegisterDTO.java
│   │   │   │   ├── LoginDTO.java
│   │   │   │   ├── PersonalDetailsDTO.java
│   │   │   │   ├── BankDetailsDTO.java
│   │   │   │   └── AddressDetailsDTO.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── PersonalDetailsRepository.java
│   │   │   │   ├── BankDetailsRepository.java
│   │   │   │   └── AddressDetailsRepository.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── PersonalDetailsService.java
│   │   │   │   ├── BankDetailsService.java
│   │   │   │   └── AddressDetailsService.java
│   │   │   └── exception/
│   │   │       └── UserAlreadyExistsException.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── register.html
│   │       │   ├── login.html
│   │       │   ├── dashboard.html
│   │       │   ├── personal-details.html
│   │       │   ├── bank-details.html
│   │       │   └── address-details.html
│   │       └── static/
│   │           └── css/
│   │               └── style.css
│   └── test/
│       └── resources/
│           └── application-test.properties (if needed)
└── pom.xml
```

## API Endpoints

### Authentication Endpoints
- `GET /register` - Display registration form
- `POST /register` - Submit registration form
- `GET /login` - Display login form
- `POST /login` - Submit login form
- `GET /logout` - Logout and invalidate session

### Dashboard Endpoints
- `GET /dashboard` - Display dashboard (requires login)
- `GET /view-data` - View all saved data (requires login)

### Details Management Endpoints
- `GET /details/personal` - Display personal details form
- `POST /details/personal` - Save personal details
- `GET /details/bank` - Display bank details form
- `POST /details/bank` - Save bank details
- `GET /details/address` - Display address details form
- `POST /details/address` - Save address details

## Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Personal Details Table
```sql
CREATE TABLE personal_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(20) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Bank Details Table
```sql
CREATE TABLE bank_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(18) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    ifsc_code VARCHAR(11) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Address Details Table
```sql
CREATE TABLE address_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    pincode VARCHAR(6) NOT NULL,
    country VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

## Notes
- Password is stored as BCrypt hash - never stored in plain text
- All validation is performed server-side and returns clear error messages
- Sessions are created upon successful login and invalidated on logout
- Data persistence is handled by JPA/Hibernate ORM
