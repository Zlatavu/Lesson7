package org.example.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {// наследуемся от BaseView

    @FindBy(xpath = "//a[@class='login']")//аннотация чтобы пользоваться PageObject найти элемент,
    private WebElement signInButton;// создали вебэлемент

    public MainPage(WebDriver driver) {// в конструктор передаем драйвер
        super(driver);
    }// создаем констуктор

    // ДЛЯ КАЖДОГО ДЕЙСТВИЯ НА СТРАНИЦЕ-СВОЙ МЕТОД
    // метод возвращает ту страницу на которую мы переходим,
    // тогда можно вызвать все в одной цепочки по итогу не создавая кажды раз новый объект Page

    @Step("Клик на кнопку войти")// отобразит этап в Allure
    public LoginPage clickSingInButton() {// метод "кликнуть на зарегистрироваться"// возвращает страницу, на которую мы переходим
        signInButton.click();// вебэлемент.клик
        return new LoginPage(driver);
    }
}

