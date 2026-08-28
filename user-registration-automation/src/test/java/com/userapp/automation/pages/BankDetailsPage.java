package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BankDetailsPage.class);

    private final By accountNumberInput = By.id("accountNumber");
    private final By accountTypeSelect = By.id("accountType");
    private final By IFSCCodeInput = By.id("IFSCCode");
    private final By bankNameInput = By.id("bankName");
    private final By saveButton = By.id("saveButton");
    private final By errorMessages = By.id("errorMessages");
    private final By successMessage = By.id("successMessage");

    public BankDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterAccountNumber(String accountNumber) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberInput));
        element.clear();
        element.sendKeys(accountNumber);
        logger.debug("Entered account number: {}", accountNumber);
    }

    public void selectAccountType(String accountType) {
        Select dropdown = new Select(driver.findElement(accountTypeSelect));
        dropdown.selectByValue(accountType);
        logger.debug("Selected account type: {}", accountType);
    }

    public void enterIFSCCode(String IFSCCode) {
        WebElement element = driver.findElement(IFSCCodeInput);
        element.clear();
        element.sendKeys(IFSCCode);
        logger.debug("Entered IFSC code: {}", IFSCCode);
    }

    public void enterBankName(String bankName) {
        WebElement element = driver.findElement(bankNameInput);
        element.clear();
        element.sendKeys(bankName);
        logger.debug("Entered bank name: {}", bankName);
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

    public String getAccountNumberValue() {
        return driver.findElement(accountNumberInput).getAttribute("value");
    }

    public String getAccountTypeValue() {
        Select dropdown = new Select(driver.findElement(accountTypeSelect));
        return dropdown.getFirstSelectedOption().getText();
    }

    public String getIFSCCodeValue() {
        return driver.findElement(IFSCCodeInput).getAttribute("value");
    }

    public String getBankNameValue() {
        return driver.findElement(bankNameInput).getAttribute("value");
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(saveButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
