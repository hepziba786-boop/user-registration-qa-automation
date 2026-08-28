package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(RegisterPage.class);

    private final By usernameInput = By.id("username");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By registerButton = By.id("registerButton");
    private final By errorMessages = By.id("errorMessages");
    private final By successMessage = By.id("successMessage");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        element.clear();
        element.sendKeys(username);
        logger.debug("Entered username: {}", username);
    }

    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
        logger.debug("Entered email: {}", email);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
        logger.debug("Entered password");
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement element = driver.findElement(confirmPasswordInput);
        element.clear();
        element.sendKeys(confirmPassword);
        logger.debug("Entered confirm password");
    }

    public void clickRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        element.click();
        logger.info("Clicked Register button");
    }

    public String getErrorMessage() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessages));
            String message = element.getText();
            logger.debug("Error message: {}", message);
            return message;
        } catch (Exception e) {
            logger.debug("No error message displayed");
            return null;
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String message = element.getText();
            logger.debug("Success message: {}", message);
            return message;
        } catch (Exception e) {
            logger.debug("No success message displayed");
            return null;
        }
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(registerButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clearAllFields() {
        driver.findElement(usernameInput).clear();
        driver.findElement(emailInput).clear();
        driver.findElement(passwordInput).clear();
        driver.findElement(confirmPasswordInput).clear();
        logger.debug("Cleared all form fields");
    }

}
