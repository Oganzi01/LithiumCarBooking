package com.carbooking.gui.tests;

import com.carbooking.gui.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityTests extends TestBase {

    @Test
    public void testSqlInjectionPrevention() {
        homePage.clickOnLoginLink();
        // Попытка SQL-инъекции в поле email
        loginPage.login("' OR '1'='1", "password");
        Assert.assertTrue(loginPage.isErrorMessagePresent(), "Система должна показать ошибку на SQL-инъекцию!");
        Assert.assertFalse(homePage.isLogoutButtonPresent(), "Уязвимость: вход выполнен через SQL-инъекцию!");
    }

    @Test
    public void testXssAttackPrevention() {
        homePage.clickOnLoginLink();
        // Попытка XSS атаки (скрипт в поле)
        loginPage.login("<script>alert('XSS')</script>", "password");
        Assert.assertFalse(homePage.isLogoutButtonPresent(), "Уязвимость: скрипт мог быть исполнен!");
    }

    @Test
    public void testBruteForceProtectionHint() {
        homePage.clickOnLoginLink();
        // Простая проверка, что при неверных данных доступ закрыт
        loginPage.login("non_existent_user@gmail.com", "wrong_pass");
        Assert.assertFalse(homePage.isLogoutButtonPresent(), "Доступ разрешен несуществующему пользователю!");
    }
}
