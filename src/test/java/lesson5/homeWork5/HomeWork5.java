package lesson5.homeWork5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeWork5 {
    // сразу в самом классе объявляем поля необходимых классов, и перед каждым тестом будем их инициализировать
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll//перед всеми тестами зарегистирируем драйвер
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();//инициализируем драйвер- присваеваем ему объект
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));// инициализируем webDriverWait, передавая ему экземпляр драйвера и  указываем максимальное время ожидания
        actions = new Actions(driver);// инициализируем actions, передавая ему экземпляр драйвера
        driver.get("https://translate.yandex.ru/?lang=ru-en&text=%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C%20%D0%B4%D0%BE%D0%BC%D0%B0%D1%88%D0%BD%D0%B5%D0%B5%20%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5");
    }

    @Test
    @DisplayName("Тест_6: Выбор альтернативного перевода  в дополнительном окне, появляющемся при выделении  конкретного слова в поле с исходным тестом")
    void test6() throws InterruptedException {
        //Thread.sleep(30000);// на случай проверки на робота- время на ввод данных вручную
        actions.moveToElement(driver.findElement(By.xpath("//div[@id=\"speller\"]/span")), -140,0)
        // переходим на элемент по локатору и задаем точное положение курсора по осям x и y (в данном случае передвигаемся на начало текста влево)
                .clickAndHold()// кликаем и удерживаем
                .moveByOffset(70,0)// задаем смешение курсора( в данном случае вправо на одно слово)
                .release()// отпускаем мышь
                .perform();
        // дожидаемся появления элемента на странице
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"dict-meanings-value\"]/span[.=\"do\"]")));
        driver.findElement(By.xpath("//div[@class=\"dict-meanings-value\"]/span[.=\"do\"]")).click();// находим жлемент по локатору и кликаем
        Thread.sleep(5000);//безусловное ожидание для удобства наблюдение за прохождение теста( в идеале не нужно)
        // Assertion: проверим, что содержимое страницы изменилось и в url теперь содержится текст "do"
        Assertions.assertTrue(driver.getCurrentUrl().contains("text=do"));

    }
    @Test
    @DisplayName("Тест_7: Выделение соответствующего слова в левом поле  с исходным тестом при наведении на конкретное слово в правом поле с переводом текста  ")
    void test7() throws InterruptedException {
        //Thread.sleep(30000);// на случай проверки на робота- время на ввод данных вручную
        actions.moveToElement(driver.findElement(By.xpath("//span[@class =\"translation-word translation-chunk\" and .=\"doing\"]")))
                //наводим мышь на элемент ( находим его по локатору)
                .perform();
        Thread.sleep(5000);//безусловное ожидание для удобства наблюдение за прохождение теста( в идеале не нужно)
        // Assertion: т.к. при наведении на элемент меняется название класса,в котором этот элемент находится, в проверке мы проверим есть ли измененное название класса на странице
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class=\"textinput textlayer textlayer_src textlayer_measurer textlayer_measurer_alignment\"]")).isEnabled());
    }
    @Test
    @DisplayName("Тест_8: Выбор альтернативного варианта  перевода  конкретного слова в дополнительном окне, появляющемся при клике на это слово в поле с переводом ")
    void test8() throws InterruptedException {
         //Thread.sleep(30000);// на случай проверки на робота- время на ввод данных вручную
        actions.moveToElement(driver.findElement(By.xpath("//span[@class =\"translation-word translation-chunk\" and .=\"doing\"]")))
                //наводим мышь на элемент- конкретное слово ( находим его по локатору)
                .click()//кликаем на элемент
                .perform();
        // дожидаемся появления доп окошка
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"listbox-option state-unselectable\" and @data-value=\"do\" ]")));
        driver.findElement(By.xpath("//div[@class=\"listbox-option state-unselectable\" and @data-value=\"do\" ]")).click();// находим жлемент по локатору и кликаем
        Thread.sleep(5000);//безусловное ожидание для удобства наблюдение за прохождение теста( в идеале не нужно)
        // Assertion: т.к. при выборе альтернативного перевода из списка появляется определенный span, то в проверке проверим если ли этот span на странице
        Assertions.assertTrue(driver.findElement(By.xpath("//pre[@id=\"translation\" ]//span[.=\"do\"]")).isEnabled());
    }

    @AfterEach// после каждого теста убиваем драйвер вызывая метод quit
    void tearDown() {
        driver.quit();
    }


}
