package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("loginButton");
    private final By errorMessages = By.id("errorMessages");
    private final By rememberMe = By.id("rememberMe");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        element.clear();
        element.sendKeys(username);
        logger.debug("Entered username: {}", username);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
        logger.debug("Entered password");
    }

    public void clickLoginButton() {
        WebElement element = driver.findElement(loginButton);
        element.click();
        logger.info("Clicked Login button");
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

    public void checkRememberMe() {
        WebElement element = driver.findElement(rememberMe);
        if (!element.isSelected()) {
            element.click();
            logger.debug("Checked Remember Me checkbox");
        }
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
