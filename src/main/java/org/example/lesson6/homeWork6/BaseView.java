package org.example.lesson6.homeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseView {
    WebDriver driver;// объявляем драйвер
    WebDriverWait webDriverWait;// объявляем WebDriverWait
    Actions actions;// объявляем Actions


    public BaseView(WebDriver driver) throws InterruptedException {// в конструктор передаем драйвер
        this.driver = driver;// инициализируем его
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));// инициализируем WebDriverWait
        actions = new Actions(driver);// инициализируем Actions
        driver.get("https://translate.yandex.ru/?lang=ru-en&text=%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C%20%D0%B4%D0%BE%D0%BC%D0%B0%D1%88%D0%BD%D0%B5%D0%B5%20%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5");
        PageFactory.initElements(driver, this);// передаем  на вход драйвер и страницу
        Thread.sleep(30000); // ожидание на случай проверки на робота, для ввода данных вручную
    }
}
