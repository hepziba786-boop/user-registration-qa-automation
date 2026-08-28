package com.userapp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonalDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsPage.class);

    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By dateOfBirthInput = By.id("dateOfBirth");
    private final By genderSelect = By.id("gender");
    private final By saveButton = By.id("saveButton");
    private final By errorMessages = By.id("errorMessages");
    private final By successMessage = By.id("successMessage");

    public PersonalDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        element.clear();
        element.sendKeys(firstName);
        logger.debug("Entered first name: {}", firstName);
    }

    public void enterLastName(String lastName) {
        WebElement element = driver.findElement(lastNameInput);
        element.clear();
        element.sendKeys(lastName);
        logger.debug("Entered last name: {}", lastName);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        WebElement element = driver.findElement(dateOfBirthInput);
        element.clear();
        element.sendKeys(dateOfBirth);
        logger.debug("Entered date of birth: {}", dateOfBirth);
    }

    public void selectGender(String gender) {
        Select dropdown = new Select(driver.findElement(genderSelect));
        dropdown.selectByValue(gender);
        logger.debug("Selected gender: {}", gender);
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

    public String getFirstNameValue() {
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    public String getLastNameValue() {
        return driver.findElement(lastNameInput).getAttribute("value");
    }

    public String getDateOfBirthValue() {
        return driver.findElement(dateOfBirthInput).getAttribute("value");
    }

    public String getGenderValue() {
        Select dropdown = new Select(driver.findElement(genderSelect));
        return dropdown.getFirstSelectedOption().getText();
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(saveButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
