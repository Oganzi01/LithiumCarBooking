package com.carbooking.gui.tests.all;

import com.carbooking.gui.tests.SecurityTests; // Тот самый импорт, чтобы не было красным
import com.carbooking.gui.tests.positive.LoginPositiveTests;
import com.carbooking.gui.tests.positive.BookingPositiveTests;
import com.carbooking.gui.tests.positive.RegistrationPositiveTests;
import com.carbooking.gui.tests.negative.LoginNegativeTests;
import com.carbooking.gui.tests.negative.BookingNegativeTests;
import org.testng.annotations.Test;
import org.testng.TestNG;

public class AllTestsRunner {

    @Test
    public void runEverything() {
        TestNG testng = new TestNG();
        // Добавляем сюда все классы тестов
        testng.setTestClasses(new Class[] {
                LoginPositiveTests.class,
                BookingPositiveTests.class,
                RegistrationPositiveTests.class,
                LoginNegativeTests.class,
                BookingNegativeTests.class,
                SecurityTests.class, // Добавили новый класс в запуск
                FullWorkflowTest.class
        });
        testng.run();
    }
}
