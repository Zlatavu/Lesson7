package org.example.lesson_3.homeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test_1_TypeValidValue {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//установка актуальной версии браузера с помощью WebDriverManager
        // задаем параметры работы для браузера ( пока закомментим, чтобы можно было просмотреть, как отработает тест)
//       ChromeOptions chromeOptions = new ChromeOptions();// с помощью конструктора создаем экземпляр класса ChromeOptions
//        chromeOptions.addArguments("--headless");// добавляем аргумент headless-работа без рафического интерфейса
//        chromeOptions.addArguments("--disable-notifications");// добавляем аргумент disable-notifications - работа без всплывающих уведомлений
        //WebDriver webDriver = new ChromeDriver(chromeOptions);//передавая в конструктор экземпляр chromeOptions создаем экземпляр класса драйвера
        WebDriver webDriver = new ChromeDriver();//с помощью конструктора создаем экземпляр класса
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//настраиваем браузер на ожидание с лимитом в 3 секнды

        webDriver.get("https://translate.yandex.ru/");//переходим на страницу
        String validValue = "делать домашнее задание";// задаем и инициализируем переменную с валидным значением для ввода текста
        webDriver.findElement(By.id("fakeArea")).sendKeys(validValue);// находим элемент по локатору(id), вводим валидное значение
        Thread.sleep(5000);//безусловное ожидание для удобства наблюдение за прохождение теста( в идеале не нужно)
        webDriver.quit();// закрываем окно браузера


    }


}
