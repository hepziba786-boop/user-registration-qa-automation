package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(AddressDetailsPage.class);

    private final By streetInput = By.id("street");
    private final By cityInput = By.id("city");
    private final By stateInput = By.id("state");
    private final By pincodeInput = By.id("pincode");
    private final By countryInput = By.id("country");
    private final By saveButton = By.id("saveButton");
    private final By errorMessages = By.id("errorMessages");
    private final By successMessage = By.id("successMessage");

    public AddressDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterStreet(String street) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(streetInput));
        element.clear();
        element.sendKeys(street);
        logger.debug("Entered street: {}", street);
    }

    public void enterCity(String city) {
        WebElement element = driver.findElement(cityInput);
        element.clear();
        element.sendKeys(city);
        logger.debug("Entered city: {}", city);
    }

    public void enterState(String state) {
        WebElement element = driver.findElement(stateInput);
        element.clear();
        element.sendKeys(state);
        logger.debug("Entered state: {}", state);
    }

    public void enterPincode(String pincode) {
        WebElement element = driver.findElement(pincodeInput);
        element.clear();
        element.sendKeys(pincode);
        logger.debug("Entered pincode: {}", pincode);
    }

    public void enterCountry(String country) {
        WebElement element = driver.findElement(countryInput);
        element.clear();
        element.sendKeys(country);
        logger.debug("Entered country: {}", country);
    }

    public void clickSaveButton() {
        WebElement element = driver.findElement(saveButton);
        element.click();
        logger.info("Clicked Save button");
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

    public String getStreetValue() {
        return driver.findElement(streetInput).getAttribute("value");
    }

    public String getCityValue() {
        return driver.findElement(cityInput).getAttribute("value");
    }

    public String getStateValue() {
        return driver.findElement(stateInput).getAttribute("value");
    }

    public String getPincodeValue() {
        return driver.findElement(pincodeInput).getAttribute("value");
    }

    public String getCountryValue() {
        return driver.findElement(countryInput).getAttribute("value");
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(saveButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
