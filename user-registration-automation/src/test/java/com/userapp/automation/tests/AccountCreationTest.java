package com.userapp.automation.tests;

import com.userapp.automation.pages.RegisterPage;
import com.userapp.automation.utils.BaseTest;
import com.userapp.automation.utils.ScreenshotUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.userapp.automation.utils.TestResultWatcher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultWatcher.class)
@DisplayName("Account Creation Tests")
public class AccountCreationTest extends BaseTest {

    @Test
    @DisplayName("S1_TC01 - Valid registration with all correct data")
    public void test_S1_TC01_validRegistration() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        assertTrue(registerPage.isDisplayed(), "Register page should be displayed");
        
        registerPage.enterUsername("testuser1");
        registerPage.enterEmail("testuser1@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        // Verify redirect to login
        assertEquals(baseUrl + "/login", driver.getCurrentUrl(), "Should redirect to login page");
    }

    @Test
    @DisplayName("S1_TC02 - Registration with username already exists")
    public void test_S1_TC02_usernameAlreadyExists() {
        // Pre-setup: Create a user first
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.enterUsername("existinguser");
        registerPage.enterEmail("existing@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        // Now try to register with same username
        driver.navigate().to(baseUrl + "/register");
        registerPage.enterUsername("existinguser");
        registerPage.enterEmail("newemail@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("Username already exists"), "Should show username exists error");
    }

    @Test
    @DisplayName("S1_TC03 - Registration with email already exists")
    public void test_S1_TC03_emailAlreadyExists() {
        // Pre-setup: Create a user first
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.enterUsername("user_tc03");
        registerPage.enterEmail("existing_tc03@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        // Now try to register with same email
        driver.navigate().to(baseUrl + "/register");
        registerPage.enterUsername("newuser_tc03");
        registerPage.enterEmail("existing_tc03@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("Email already exists"), "Should show email exists error");
    }

    @Test
    @DisplayName("S1_TC04 - Registration with empty username")
    public void test_S1_TC04_emptyUsername() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S1_TC05 - Registration with empty email")
    public void test_S1_TC05_emptyEmail() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S1_TC06 - Registration with empty password")
    public void test_S1_TC06_emptyPassword() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S1_TC07 - Registration with empty confirm password")
    public void test_S1_TC07_emptyConfirmPassword() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S1_TC08 - Registration with invalid email format")
    public void test_S1_TC08_invalidEmailFormat() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("invalidemail");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("Invalid") || errorMsg.contains("email"), "Should show email format error");
    }

    @Test
    @DisplayName("S1_TC09 - Registration with password less than 8 characters")
    public void test_S1_TC09_passwordTooShort() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("Pass12");
        registerPage.enterConfirmPassword("Pass12");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("at least 8"), "Should show password length error");
    }

    @Test
    @DisplayName("S1_TC10 - Registration with mismatched confirm password")
    public void test_S1_TC10_mismatchedPassword() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("testuser");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass456");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("do not match"), "Should show password mismatch error");
    }

    @Test
    @DisplayName("S1_TC11 - Registration with username containing special characters")
    public void test_S1_TC11_specialCharactersInUsername() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("test@user#123");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("alphanumeric"), "Should show special character error");
    }

    @Test
    @DisplayName("S1_TC12 - Registration with username exceeding max length")
    public void test_S1_TC12_usernameTooLong() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        String longUsername = "thisusernameistoolongandexceedsthemaximumallowedlength";
        registerPage.enterUsername(longUsername);
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("exceed"), "Should show max length error");
    }

    @Test
    @DisplayName("S1_TC13 - Registration with username less than 3 characters")
    public void test_S1_TC13_usernameTooShort() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        
        registerPage.enterUsername("ab");
        registerPage.enterEmail("test@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        String errorMsg = registerPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("at least 3"), "Should show min length error");
    }

}
