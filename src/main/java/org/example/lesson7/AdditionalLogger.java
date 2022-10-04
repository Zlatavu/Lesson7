package org.example.lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class AdditionalLogger implements WebDriverListener {//
    // сигнатуру берем из listener, а внутрянку пишем сами в зависимости от того что хотим
    public void beforeFindElement(WebDriver driver, By locator) {//на вход драйвет и локатор
        Allure.step("Ищем элемент по локатору:" + locator);// добавляем в allure step
    }

    public void beforeQuit(WebDriver driver) {
        // переводим драйвер к типу TakesScreenshot и делаем скриншот 9 allure принимает стрим из байт
        // Allure.addAttachment("Скриншот перед закрытием браузера",
        //new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}


