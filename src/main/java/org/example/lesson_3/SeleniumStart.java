package org.example.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumStart {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");//прописываем вручную расположение драйвера для обращения к нему
        WebDriver webDriver = new ChromeDriver();//с помощью конструктора драйвера создаем экземпляр драйвера
        webDriver.get("https://google.com");// переходим на страницу
        Thread.sleep(5000);//сон-ожидание
        webDriver.quit();//закрытие окна(всех вкладок)
        //webDriver.close();//закрытие вкладки

        WebDriverManager.firefoxdriver().setup();//установка актуальной версии браузера с помощью WebDriverManager
        WebDriver firefoxDriver =new FirefoxDriver();//с помощью конструктора драйвера создаем экземпляр драйвера
        firefoxDriver.get("https:ya.ru");//переходим на страницу
        Thread.sleep(5000);
        firefoxDriver.quit();

    }}
