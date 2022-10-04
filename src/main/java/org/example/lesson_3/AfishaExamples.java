package org.example.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaExamples {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();//установка актуальной версии браузера с помощью WebDriverManager

        WebDriver webDriver = new ChromeDriver();//с помощью конструктора создаем экземпляр класса
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//настраиваем браузер на ожидание с лимитом в 3 секнды

        webDriver.get("https://www.afisha.ru/");//преходим на страницу

        WebElement inputSearch = webDriver.findElement(By.xpath("//input[@placeholder='Событие, актер, место']"));//создаем и инициализируем переменную для элемента
        inputSearch.sendKeys("Брат");//вводим в элемент текст

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));//с помощью конструктора создаем экземпляр класса WebdriverWait
        //webDriverWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[.='Брат']"))));// если элемент изначально прописан в HTML-ке то можно применить visibilityOf
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Брат']")));// если не видим, то применяем видимсть по локатору

        webDriver.findElement(By.xpath("//div[.='Брат']")).click();//находим элемент и кликаем

        Thread.sleep(5000);// безусловное ожидание
        webDriver.quit();// закрываем окно
    }

}
