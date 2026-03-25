package com.carbooking.gui.tests.positive;

import com.carbooking.gui.core.TestBase;
import com.carbooking.gui.pages.BookingPage;
import com.carbooking.gui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingPositiveTests extends TestBase {

    @Test
    public void testCarBookingSuccess() {
        // Переходим на логин
        app.driver.get("http://localhost:5173/login");
        LoginPage loginPage = new LoginPage(app.driver);

        // ВАЖНО: Убедиться, что этот пользователь существует в базе!
        loginPage.login("tester1774119303709@gmail.com", "GanzQA2026!");

        // Переход на главную для бронирования
        app.driver.get("http://localhost:5173/");
        BookingPage bPage = new BookingPage(app.driver);

        // Заполнение данных
        bPage.fillDatesAndSearch("28032026", "30032026");
        bPage.clickFirstAvailableCar();

        Assert.assertTrue(bPage.isBookingSuccessful(), "Бронирование не завершено!");
    }
}