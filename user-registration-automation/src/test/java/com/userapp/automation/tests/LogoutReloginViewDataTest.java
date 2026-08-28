package com.userapp.automation.tests;

import com.userapp.automation.pages.*;
import com.userapp.automation.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.userapp.automation.utils.TestResultWatcher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultWatcher.class)
@DisplayName("Logout, Re-login and View Data Tests")
public class LogoutReloginViewDataTest extends BaseTest {

    private void setupCompleteProfile() {
        // Register
        navigateTo("/register");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.enterUsername("testuser3");
        registerPage.enterEmail("testuser3@gmail.com");
        registerPage.enterPassword("SecurePass123");
        registerPage.enterConfirmPassword("SecurePass123");
        registerPage.clickRegisterButton();
        
        // Login
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        // Add personal details
        navigateTo("/details/personal");
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver, wait);
        personalPage.enterFirstName("John");
        personalPage.enterLastName("Doe");
        personalPage.enterDateOfBirth("1990-01-01");
        personalPage.selectGender("Male");
        personalPage.clickSaveButton();
        
        // Add bank details
        navigateTo("/details/bank");
        BankDetailsPage bankPage = new BankDetailsPage(driver, wait);
        bankPage.enterAccountNumber("1234567890123456");
        bankPage.selectAccountType("Savings");
        bankPage.enterIFSCCode("SBIN0001234");
        bankPage.enterBankName("State Bank of India");
        bankPage.clickSaveButton();
        
        // Add address details
        navigateTo("/details/address");
        AddressDetailsPage addressPage = new AddressDetailsPage(driver, wait);
        addressPage.enterStreet("123 Main Street");
        addressPage.enterCity("New York");
        addressPage.enterState("NY");
        addressPage.enterPincode("10001");
        addressPage.enterCountry("USA");
        addressPage.clickSaveButton();
    }

    @Test
    @DisplayName("S3_TC01 - Successful logout")
    public void test_S3_TC01_successfulLogout() {
        setupCompleteProfile();
        
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Should redirect to login page
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page after logout");
    }

    @Test
    @DisplayName("S3_TC02 - Accessing dashboard after logout")
    public void test_S3_TC02_accessDashboardAfterLogout() {
        setupCompleteProfile();
        
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Try to access dashboard directly
        navigateTo("/dashboard");
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page");
    }

    @Test
    @DisplayName("S3_TC03 - Accessing details pages after logout")
    public void test_S3_TC03_accessDetailsAfterLogout() {
        setupCompleteProfile();
        
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Try to access personal details
        navigateTo("/details/personal");
        assertTrue(driver.getCurrentUrl().contains("login"), "Should redirect to login page");
    }

    @Test
    @DisplayName("S3_TC04 - Login after logout and view personal details")
    public void test_S3_TC04_reloginViewPersonalDetails() {
        setupCompleteProfile();
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        // Navigate to personal details
        navigateTo("/details/personal");
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver, wait);
        assertTrue(personalPage.isDisplayed(), "Personal details page should be displayed");
        assertEquals("John", personalPage.getFirstNameValue(), "First name should be persisted");
        assertEquals("Doe", personalPage.getLastNameValue(), "Last name should be persisted");
    }

    @Test
    @DisplayName("S3_TC05 - Login after logout and view bank details")
    public void test_S3_TC05_reloginViewBankDetails() {
        setupCompleteProfile();
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        // Navigate to bank details
        navigateTo("/details/bank");
        BankDetailsPage bankPage = new BankDetailsPage(driver, wait);
        assertTrue(bankPage.isDisplayed(), "Bank details page should be displayed");
        assertEquals("1234567890123456", bankPage.getAccountNumberValue(), "Account number should be persisted");
    }

    @Test
    @DisplayName("S3_TC06 - Login after logout and view address details")
    public void test_S3_TC06_reloginViewAddressDetails() {
        setupCompleteProfile();
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        // Navigate to address details
        navigateTo("/details/address");
        AddressDetailsPage addressPage = new AddressDetailsPage(driver, wait);
        assertTrue(addressPage.isDisplayed(), "Address details page should be displayed");
        assertEquals("123 Main Street", addressPage.getStreetValue(), "Street should be persisted");
    }

    @Test
    @DisplayName("S3_TC07 - Verify personal details data persistence")
    public void test_S3_TC07_personalDetailsDataPersistence() {
        setupCompleteProfile();
        
        // Verify data is there
        navigateTo("/details/personal");
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver, wait);
        assertEquals("John", personalPage.getFirstNameValue(), "First name should be saved");
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login and verify
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/personal");
        personalPage = new PersonalDetailsPage(driver, wait);
        assertEquals("John", personalPage.getFirstNameValue(), "Data should persist after re-login");
    }

    @Test
    @DisplayName("S3_TC08 - Verify bank details data persistence")
    public void test_S3_TC08_bankDetailsDataPersistence() {
        setupCompleteProfile();
        
        // Verify data
        navigateTo("/details/bank");
        BankDetailsPage bankPage = new BankDetailsPage(driver, wait);
        assertEquals("1234567890123456", bankPage.getAccountNumberValue(), "Account number should be saved");
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login and verify
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/bank");
        bankPage = new BankDetailsPage(driver, wait);
        assertEquals("1234567890123456", bankPage.getAccountNumberValue(), "Data should persist after re-login");
    }

    @Test
    @DisplayName("S3_TC09 - Verify address details data persistence")
    public void test_S3_TC09_addressDetailsDataPersistence() {
        setupCompleteProfile();
        
        // Verify data
        navigateTo("/details/address");
        AddressDetailsPage addressPage = new AddressDetailsPage(driver, wait);
        assertEquals("123 Main Street", addressPage.getStreetValue(), "Street should be saved");
        
        // Logout
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Re-login and verify
        navigateTo("/login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("testuser3");
        loginPage.enterPassword("SecurePass123");
        loginPage.clickLoginButton();
        
        navigateTo("/details/address");
        addressPage = new AddressDetailsPage(driver, wait);
        assertEquals("123 Main Street", addressPage.getStreetValue(), "Data should persist after re-login");
    }

    @Test
    @DisplayName("S3_TC10 - Browser back button after logout")
    public void test_S3_TC10_backButtonAfterLogout() {
        setupCompleteProfile();
        
        // Logout from dashboard
        navigateTo("/dashboard");
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLogoutButton();
        
        // Use browser back button
        driver.navigate().back();
        
        // Should not access dashboard, should be on login or redirected
        assertFalse(driver.getCurrentUrl().contains("/dashboard"), "Should not access dashboard via back button");
    }

}
