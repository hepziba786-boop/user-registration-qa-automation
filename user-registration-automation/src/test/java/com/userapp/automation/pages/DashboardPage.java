package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private final By welcomeMessage = By.id("welcomeMessage");
    private final By viewPersonalDetailsLink = By.id("viewPersonalDetailsLink");
    private final By viewBankDetailsLink = By.id("viewBankDetailsLink");
    private final By viewAddressDetailsLink = By.id("viewAddressDetailsLink");
    private final By logoutButton = By.id("logoutButton");
    private final By dashboardContainer = By.id("dashboardContainer");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getWelcomeMessage() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
            return element.getText();
        } catch (Exception e) {
            logger.debug("Welcome message not found");
            return null;
        }
    }

    public void clickViewPersonalDetailsLink() {
        WebElement element = driver.findElement(viewPersonalDetailsLink);
        element.click();
        logger.info("Clicked View Personal Details link");
    }

    public void clickViewBankDetailsLink() {
        WebElement element = driver.findElement(viewBankDetailsLink);
        element.click();
        logger.info("Clicked View Bank Details link");
    }

    public void clickViewAddressDetailsLink() {
        WebElement element = driver.findElement(viewAddressDetailsLink);
        element.click();
        logger.info("Clicked View Address Details link");
    }

    public void clickLogoutButton() {
        WebElement element = driver.findElement(logoutButton);
        element.click();
        logger.info("Clicked Logout button");
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(dashboardContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
