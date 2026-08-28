package com.userapp.automation.tests;

import com.userapp.automation.pages.*;
import com.userapp.automation.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.userapp.automation.utils.TestResultWatcher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultWatcher.class)
@DisplayName("Login and Add Details Tests")
public class LoginAndAddDetailsTest extends BaseTest {

    private void setupTestUser() {
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.enterUsername("testuser2");
        registerPage.enterEmail("testuser2@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
    }

    @Test
    @DisplayName("S2_TC01 - Valid login with correct credentials")
    public void test_S2_TC01_validLogin() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        assertTrue(dashboardPage.isDisplayed(), "Dashboard should be displayed after login");
    }

    @Test
    @DisplayName("S2_TC02 - Login with wrong password")
    public void test_S2_TC02_wrongPassword() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("WrongPass123");
        loginPage.clickLoginButton();
        
        String errorMsg = loginPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("Invalid"), "Should show invalid credentials error");
    }

    @Test
    @DisplayName("S2_TC03 - Login with non-existent username")
    public void test_S2_TC03_nonExistentUsername() {
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("nonexistentuser");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        String errorMsg = loginPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
        assertTrue(errorMsg.contains("Invalid"), "Should show invalid credentials error");
    }

    @Test
    @DisplayName("S2_TC04 - Login with empty username")
    public void test_S2_TC04_emptyUsername() {
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        String errorMsg = loginPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S2_TC05 - Login with empty password")
    public void test_S2_TC05_emptyPassword() {
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.clickLoginButton();
        
        String errorMsg = loginPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S2_TC06 - Add valid personal details")
    public void test_S2_TC06_addValidPersonalDetails() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/personal");
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver, wait);
        personalPage.enterFirstName("John");
        personalPage.enterLastName("Doe");
        personalPage.enterDateOfBirth("1990-01-01");
        personalPage.selectGender("Male");
        personalPage.clickSaveButton();
        
        // Should redirect to dashboard after successful save
        assertTrue(driver.getCurrentUrl().contains("dashboard"), "Should redirect to dashboard");
    }

    @Test
    @DisplayName("S2_TC07 - Add personal details with empty firstName")
    public void test_S2_TC07_emptyFirstName() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/personal");
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver, wait);
        personalPage.enterLastName("Doe");
        personalPage.enterDateOfBirth("1990-01-01");
        personalPage.selectGender("Male");
        personalPage.clickSaveButton();
        
        String errorMsg = personalPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S2_TC11 - Add valid bank details")
    public void test_S2_TC11_addValidBankDetails() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/bank");
        BankDetailsPage bankPage = new BankDetailsPage(driver, wait);
        bankPage.enterAccountNumber("1234567890123456");
        bankPage.selectAccountType("Savings");
        bankPage.enterIFSCCode("SBIN0001234");
        bankPage.enterBankName("State Bank of India");
        bankPage.clickSaveButton();
        
        assertTrue(driver.getCurrentUrl().contains("dashboard"), "Should redirect to dashboard");
    }

    @Test
    @DisplayName("S2_TC12 - Add bank details with empty accountNumber")
    public void test_S2_TC12_emptyAccountNumber() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/bank");
        BankDetailsPage bankPage = new BankDetailsPage(driver, wait);
        bankPage.selectAccountType("Savings");
        bankPage.enterIFSCCode("SBIN0001234");
        bankPage.enterBankName("State Bank of India");
        bankPage.clickSaveButton();
        
        String errorMsg = bankPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S2_TC17 - Add valid address details")
    public void test_S2_TC17_addValidAddressDetails() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/address");
        AddressDetailsPage addressPage = new AddressDetailsPage(driver, wait);
        addressPage.enterStreet("123 Main Street");
        addressPage.enterCity("New York");
        addressPage.enterState("NY");
        addressPage.enterPincode("10001");
        addressPage.enterCountry("USA");
        addressPage.clickSaveButton();
        
        assertTrue(driver.getCurrentUrl().contains("dashboard"), "Should redirect to dashboard");
    }

    @Test
    @DisplayName("S2_TC18 - Add address details with empty street")
    public void test_S2_TC18_emptyStreet() {
        setupTestUser();
        
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser2");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/address");
        AddressDetailsPage addressPage = new AddressDetailsPage(driver, wait);
        addressPage.enterCity("New York");
        addressPage.enterState("NY");
        addressPage.enterPincode("10001");
        addressPage.enterCountry("USA");
        addressPage.clickSaveButton();
        
        String errorMsg = addressPage.getErrorMessage();
        assertNotNull(errorMsg, "Error message should be displayed");
    }

    @Test
    @DisplayName("S2_TC21 - Access personal details page without login")
    public void test_S2_TC21_accessPersonalDetailsWithoutLogin() {
        navigateTo("/details/personal");
        
        // Should redirect to login page
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page");
    }

    @Test
    @DisplayName("S2_TC22 - Access bank details page without login")
    public void test_S2_TC22_accessBankDetailsWithoutLogin() {
        navigateTo("/details/bank");
        
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page");
    }

    @Test
    @DisplayName("S2_TC23 - Access address details page without login")
    public void test_S2_TC23_accessAddressDetailsWithoutLogin() {
        navigateTo("/details/address");
        
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page");
    }

}
