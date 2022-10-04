package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ActionsTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    String address;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
    }

    @Test
    void highlightTextTest() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1200, 1200));
        driver.get("https://translate.google.com/?sl=ru&tl=en&text=test&op=translate");// переходим на страницу
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")));// дожидаемся пока элемент появится на странице
        actions.moveToElement(driver.findElement(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")), -20, 0)
               // переходим на элемент по локатору и задаем точное положение курсора по осям x и y (в данном случае передвигаемся на начало текста)
                .clickAndHold()// кликаем и удерживаем
                .moveByOffset(30,0)// задаем смешение курсора ( в данном случае вправо
                .perform();
        Thread.sleep(5000);
    }

    @Test
    void getNewExamples() throws InterruptedException {
        driver.get("https://google.com");
        ((JavascriptExecutor)driver).executeScript("alert('sdfdsf')");// создаем нативное окошко с алертом
        Thread.sleep(5000);// ждем
        driver.switchTo().alert().accept();// переключаемся на алерт. принимаем( тут зависит от назначения аллерта
        Thread.sleep(5000);

        driver.switchTo().newWindow(WindowType.TAB);// переключаемся на новое окно (TAB- вкладка, WIndow-окно)
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());// внутрь переменной tabs сохраняем список номеров вкладок(дискриптор ), полученных методом getWindowHandles
        driver.switchTo().window(tabs.get(1));// переключаемся на вкладку - достаем из списка 1 элемент- это будет новая вкладка дискриптор

        driver.get("https://ya.ru");// на новой вкладке перейдем на страницу
        Thread.sleep(2000);
        driver.switchTo().window(tabs.get(0));// переключимся на старую вкладку
        driver.close();
    }

    @AfterEach
    void killBrowser() {
        driver.quit();
    }
}
