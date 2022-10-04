package org.example.lesson7.homeWork7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class AdditionalLogger implements WebDriverListener {
    public void beforeQuit(WebDriver driver) {
        Allure.step("Тест завершен");
    }

}


