package org.example.lesson6.homeWork6;

import io.qameta.allure.Step;
import org.example.lesson6.BaseView;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class StartedPage extends BaseView {

    @FindBy(id = "fakeArea")// создаем WebElement
    private WebElement textField ;

    public StartedPage(WebDriver driver) {
        super(driver);
    }//конструктор

    @Step("Вводим валидное значение в поле 'Исходный текст'")
    public TranslatePage sendText(String validValue) throws InterruptedException {
        driver.get("https://translate.yandex.ru/");// единственный тест с отличной от остальных стартовой страницей- прописала в нем лишнее действие по переходую
        textField.sendKeys(validValue);// вводим валидное значение
        return new TranslatePage(driver);
    }




}
