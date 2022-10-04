package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.example.lesson6.MainPage;
import org.example.lesson7.AdditionalLogger;
import org.example.lesson7.JunitExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.io.ByteArrayInputStream;
import java.util.jar.JarEntry;


@Story("Добавление в козину")// сценарий
//@ExtendWith(value = JunitExtension)
public class BuyTShirtTest {// объявляем драйвер и wait
    WebDriver driver;
    MainPage mainPage;

    @RegisterExtension// регистрируем расширение
    JunitExtension testWatcher = new JunitExtension();

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();// просим webDriverManager установаить драйвер
    }

    @BeforeEach
    void initDriver() {
        //настроим selenium на работу с функционалом, который мы прописали в Listener
        // для этого передадим драйвер методу decorate, предворительно создав экземпляр EventFiringDecorator и и скормив ему экземпляр AdditionalLogger (listener)
        driver = new EventFiringDecorator( new AdditionalLogger()).decorate(driver);
        //driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    @Feature("Корзина")// раздел/ задача
    @TmsLink("")//привязка к тест кейсу в багтрекере или других системах(в ковычках указывается номер задачи)
    //в настройках в allure properties указывается адресс привязки
    @Issue("")//привязка в Jire(и не только) по номеру задачи
    // //в настройках в тест allure properties указывается адресс привязки
    void buyTShirtTest() throws InterruptedException {
        mainPage.clickSingInButton()// кликаем на зарегистрироваться
                // вводим логин и пароль вызвав метод ЛОГИН в LoginPage
                .login("spartalex93@test.test", "123456")
                // в navigationBlock выбираем WomenMenu  и кликаем на TShirts- переходим на страницу TShirtsPage
                .navigationBlock.hoverWomenMenuAndClickTShirts()
                //выбираем размер вызвав метот selectSizes(на вход передаем строку с расмером) на странице TShirtPage
                .selectSize("S")
                // выбираем цену вызвав метод moveLeftPriceSliderElement и передав на вход отклонение бегунка
                .moveLeftPriceSliderElement(10)
                // добавляем товар в корзину вызвав метод addToCartByName, передав на вход имя товара - переходим в SuccessBlock
                .addToCartByName("Faded")
                // выполняем проверку, сверив сумму - вызываем метод checkTotalSumma из  SuccessBlock
                .checkTotalSumma("$18.51");
    }

    @AfterEach
    void tearDown() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);//получаем список браузерных логов-сохраняем в переменную
        for (LogEntry log: logs) {// в цикле каждый лог передаем allure
            Allure.addAttachment("Элемент лога браузера", log.getMessage());// конкретно добавляем само сообщение об ошибке
        }
        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }

}

