package org.example.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupBrowserExamples {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//установка актуальной версии браузера с помощью WebDriverManager

        ChromeOptions chromeOptions = new ChromeOptions();// с помощью конструктора создаем экземпляр класса ChromeOptions
        chromeOptions.addArguments("--headless");// добавляем аргумент headless-работа без рафического интерфейса
        chromeOptions.addArguments("--disable-notifications");// добавляем аргумент disable-notifications - работа без всплывающих уведомлений
        chromeOptions.addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");// добавляем аргумент - для работы бота, индексирующего сайт

        WebDriver driver = new ChromeDriver(chromeOptions);// создаем экземпляр драйвера, передавая в конструктор созданный экземпляр ChromeOptions
        driver.get("https://google.com");// переходим на страницу

        Thread.sleep(5000);// безусловное ожидание
        driver.quit();// закрываем окно
    }

}
