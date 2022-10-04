package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaTest {// сразу в самом классе объявляем поля необходимых классов, и перед каждым тестом будем их инициализировать
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll//перед всеми тестами зарегистирируем драйвер
    static void registerDriver() {// название в соответствие с тем, что будем тут делать
        WebDriverManager.chromedriver().setup();//регистрируем драйвер
    }

    @BeforeEach// тут будем настраивать все, что объявили в самом классе - вызываем конструктор, инициализируем поля
    void setupBrowser() {
        driver = new ChromeDriver();//инициализируем драйвер- присваеваем ему объект
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));// инициализируем webDriverWait, передавая ему экземпляр драйвера и  указываем максимальное время ожидания
        actions = new Actions(driver);// инициализируем actions, передавая ему экземпляр драйвера
        driver.get("https://afisha.ru");//переходим на страницу( если страницы рахные- делаем это в самом методе
    }

    @Test
    void goToOkkoTest() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("let element = document.evaluate(\"//div[@data-test='HONEY-AD AD-ad_1']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)\n" +
                "element.singleNodeValue.remove()");
        // ((приводим драйвер к типу JavascriptExecutor)драйвер). теперь можем вызвать метод executeScript ( сюда вставляем javascript код( можно загуглить нужный)
        actions.scrollToElement(driver.findElement(By.xpath("//button[.='Подписаться']"))).perform();
        Thread.sleep(5000);

        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))// вызываем метод "перейти к элементу" в классе actions
                // ему передаем команду для драйвера- найти элемент по локатору
                .perform();//запускаем цепочку методов
        driver.findElement(By.xpath("//div[@data-test='HEADER-MENU']//a[.='Скоро онлайн в Okko']")).click();// находим элемент по локатору и кликаем
        Assertions.assertTrue(driver.getCurrentUrl().contains("okko"));// проводим проверку для теста- тест пройден если в текущем url появилось слово okko
    //вызываем метод assertTrue ы классе ассершнс ( передаем ему текущей url. содержащий "okko") - если содержит-вернет true
    }

    @Test
    void authTest() {// тест для авторизации
        driver.findElement(By.xpath("//button[.='Войти']")).click();// находим элемент и кликаем
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'login')]")));// переключаемся на iframe с полями для авторизацией
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));// дожидаемся пока элемент появится
        driver.findElement(By.id("login")).sendKeys("spartalex1993");// передаем данные логина в найденный элемент
        driver.findElement(By.id("password")).sendKeys("Test4test");// передаем данные пароля в найденный элемент
        webDriverWait.until(d -> d.findElement(By.id("login")).getAttribute("value").contains("@rambler.ru"));
        driver.findElement(By.xpath("//button[.='Войти']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header//a//div[contains(.,'Избранное') and preceding-sibling::span]")));// доэидаемся пока появится раздел избранное( он есть только у авторизованных пользователей)
        Assertions.assertTrue(driver.findElement(By.xpath("//header//a//div[contains(.,'Избранное') and preceding-sibling::span]")).isDisplayed());// ассершн что элемент избранное отображается
    }

    @AfterEach
    void tearDown() {// стандартное имя метода, где все подищаем
        driver.quit();// в нашем случае убиваем драйвер вызывая метод quit
    }
}
