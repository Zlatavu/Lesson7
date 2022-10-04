package lesson6.homeWork6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.lesson6.homeWork6.StartedPage;
import org.example.lesson6.homeWork6.TranslatePage;
import org.example.lesson7.AdditionalLogger;
import org.example.lesson7.JunitExtension;
import org.junit.jupiter.api.*;
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


//@Story("Выбор альтернативного варианта перевода ")
//@Story("Первоначальный ввод текста ")
//@Story("Выделение соответствующего слова ")
//@Story("Изменение языка")

@Feature("Перевод текста")
public class TranslateTests {
    WebDriver driver;
    TranslatePage translatePage;
    StartedPage startedPage;

    @RegisterExtension// регистрируем расширение
    JunitExtension testWatcher = new JunitExtension();


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();// просим webDriverManager установаить драйвер
    }


    @BeforeEach
    void initDriver() throws InterruptedException {//пред каждым тестом делаем все настройки
        driver = new ChromeDriver();
        startedPage = new StartedPage(driver);
        translatePage = new TranslatePage(driver);
        //настроим selenium на работу с функционалом, который мы прописали в Listener
        // для этого передадим драйвер методу decorate, предворительно создав экземпляр EventFiring
        driver = new EventFiringDecorator( new AdditionalLogger()).decorate(driver);

    }

    @Test
    @Story("Первоначальный ввод текста ")
    @DisplayName("Тест_1: Ввод валидных данных в поле ввода для исходного текста  ")
    public void test1() throws InterruptedException {
        startedPage.sendText("делать домашнее задание")//вводим валидные данные-в результате переходим на translate page
                .checkSendText();// assertion
    }
    @Test
    @Story("Выбор альтернативного варианта перевода ")
    @DisplayName("Тест_2: Выбор альтернативного перевода  в разделе \"Словарь\" ")
    public void test2() throws InterruptedException {
        translatePage.selectValueInDictionary()
                .checkSelectValueInDictionary();// assertion
    }

    @Test
    @Story("Выбор альтернативного варианта перевода ")
    @DisplayName("Тест_3: Выбор альтернативного варианта  в разделе \"Примеры использования\" ")
    public void test3() throws InterruptedException {
        translatePage.selectValueInExamples()
                .checkSelectValueInExamples();// assertion
    }

    @Test
    @Story("Изменение языка")
    @Feature("Язык")
    @DisplayName("Тест_4: Изменение исходного языка с помощью кнопки \"переключение направления\" ")
    public void test4() throws InterruptedException {
        translatePage.selectLanguageUsingSwapbutton()
                     .checkSelectLanguageUsingSwapbutton();// assertion
    }

    @Test
    @Story("Изменение языка")
    @Feature("Язык")
    @DisplayName("Тест_5: Изменение исходного языка с помощью кнопки \"переключение направления\" ")
    public void test5() throws InterruptedException {
        translatePage.selectLanguageInLanguageList()
                     .checkSelectLanguageInLanguageList();// assertion
    }

    @Test
    @Story("Выбор альтернативного варианта перевода ")
    @DisplayName("Тест_6: Выбор альтернативного перевода  в дополнительном окне, появляющемся при выделении  конкретного слова в поле с исходным тестом")
    public void test6 () throws InterruptedException {
        translatePage.selectTranslateVariantOfSingleWorldInLeftPart()
                .checkSelectTranslateVariantOfSingleWorldInLeftPart();//assertion
    }

    @Test
    @Story("Выделение соответствующего слова ")
    @DisplayName("Тест_7: Выделение соответствующего слова в левом поле  с исходным тестом при наведении на конкретное слово в правом поле с переводом текста ")
    public void test7 () throws InterruptedException {
        translatePage.highlightingCorrespondingWord()
                .checkHighlightingCorrespondingWord();//assertion
    }

    @Test
    @Story("Выбор альтернативного варианта перевода ")
    @DisplayName("Тест_8: Выбор альтернативного варианта  перевода  конкретного слова в дополнительном окне, появляющемся при клике на это слово в поле с переводом")
    public void test8 () throws InterruptedException {
        translatePage.selectTranslateValueInRightPart()
                .checkSelectTranslateValueInRightPart();
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
