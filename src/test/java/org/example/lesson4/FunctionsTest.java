package org.example.lesson4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FunctionsTest {
    // уровни логирования:
    //trace- самый подробный уровень- по стекам
    //debug- подробный уровень
    //info -
    //warn - предупреждения
    //error - ошибки

    //принято сохдавать экземпляр класса Logger сразу в классе
private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);


    @BeforeAll//выполняется 1 раз перед всеми тестами-тут предусловия для всех тестов- настройка драйвера, подготовка базы
   static void beforeAll(){// beforeAll всегда статичный!
        System.out.println("BeforeAll");
        logger.info("logger-info-beforeAll");
        // WebDriverManager.chromedriver().setup();// просто настройим тут драйвер
       // WebDriver driver = new ChromeDriver();

    }
   @BeforeEach//выполняется 1 раз перед каждым тестом- тут тоже настройки окружения и предусловия (например очищать что-то или создавать)
   void beforeEach(){// не обязан быть статическим
       System.out.println("BeforeEach");
       logger.debug("logger-info-beforeEach");
   }
    @Test
    @Disabled("здесь причина пропуска теста")// задизейблить- пропустить тест, не выполнять его по каким-то причинам
    @DisplayName("Проверка теста isPalindrome валидным значение 123321")// отображает имя- чтобы не каверкать названия тестов
    void isPalindromeTest(){
        logger.info("logger-Test1 running");
        boolean result = new Functions().isPalindrome("123321");//  в переменную типа boolean сохраняет результат работы методв isPalindrome
        // если метод не статический, то для его вызова необходимо создать эеземплар класса Functions, который вызовет метод
        Assertions.assertEquals(true,result);// Класс Проверкa (Assertions).метода-assertEquals- проверяется на соответствие ожидаемый результат и
        // фактический(ожидаемый-true, фактический берем из переменной результата)
    }
    @ParameterizedTest//позволяет запустить один тест с разными тестовыми  данными
    @ValueSource(strings = {"123321","1234321"})// DataProvider-источник тестовых данных (тип данных в массиве)
    @DisplayName("Параметризованный тест2")// отображает имя- чтобы не каверкать названия тестов
    void isPalindromeTest2(String testWord){//на вход будет принимать строку-тестовые данные-строку
        logger.info("logger-Test2 running");
        boolean result = new Functions().isPalindrome(testWord);//  в переменную типа boolean сохраняет результат работы методв isPalindrome
        // если метод не статический, то для его вызова необходимо создать эеземплар класса Functions, который вызовет метод
        Assertions.assertEquals(true,result);// Класс Проверкa (Assertions).метода-assertEquals- проверяется на соответствие ожидаемый результат и
        // фактический(ожидаемый-true, фактический берем из переменной результата)
    }
    @ParameterizedTest
    @CsvSource({"123, false","1234321,true"})//источник данных -массив- входные данные + ожидаемый результат
    void isPalindrome(String testWord, boolean expectedResult){// на вход будет принимать тестовые данные-строки и ожидаемый результат- boolean
        logger.info("logger-Test3 running");
        Assertions.assertEquals(expectedResult, new Functions().isPalindrome(testWord));
        //класс Assertions.метод assertEquals( переменная-ожидаемый, результат вызова метода isPalindrome-на вход переменная тестовое слово))
}
    @ParameterizedTest
    @MethodSource( "catAndAgeDataProvider")//источник данных-метод(его создали ниже)
    void catAndAge(Cat cat,Integer age){//в тест передакем объект - кота и возраст
        logger.debug("logger-Test4 running");
        Assertions.assertEquals(cat.getAge(),age);
        //Класс Assertions. метод ( возрат кота из объекта, переменная возраст)
    }
    //  в качестве провайдера используем не готовую анннотацию, а метод который возвращает объект
    private static List<Arguments> catAndAgeDataProvider(){// Arguments позволяет передавать массив из разнородных объектов
        logger.debug("logger-Test5 running");
    return Arrays.asList(
            Arguments.of(new Cat("Test1",10),10),
            Arguments.of(new Cat("Test2",11),12)

    );
}



    @AfterAll// выполняется один раз после всех тестов
    static void afterAll(){// afterAll всегда статический
        logger.debug("logger-afterAll");
        System.out.println("AfterAll");
}
    @AfterEach//выполняется после каждого теста
    void afterEach(){
        System.out.println("AfterEach");
        logger.debug("logger-afterEach");
        //WebDriver driver = new ChromeDriver();
        //driver.quit();
    }






}
