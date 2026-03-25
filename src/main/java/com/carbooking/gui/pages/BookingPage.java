package com.carbooking.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BookingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "pickupDate")
    private WebElement pickupDateInput;

    @FindBy(name = "returnDate")
    private WebElement returnDateInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    // Локатор для кнопок бронирования
    private By carButtons = By.xpath("//button[contains(text(), 'Book Now') or contains(text(), 'Rent')]");

    public BookingPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void fillDatesAndSearch(String pickup, String dropoff) {

        wait.until(ExpectedConditions.visibilityOf(pickupDateInput));
        pickupDateInput.sendKeys(pickup);
        returnDateInput.sendKeys(dropoff);
        searchButton.click();
    }

    public void clickFirstAvailableCar() {

        WebElement firstCar = wait.until(ExpectedConditions.elementToBeClickable(carButtons));
        firstCar.click();
    }

    public boolean isBookingSuccessful() {
        try {

            return wait.until(ExpectedConditions.urlContains("confirmation"))
                    || driver.getPageSource().contains("Successfully");
        } catch (Exception e) {
            return false;
        }
    }
}