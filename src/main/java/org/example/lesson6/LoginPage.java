package org.example.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {// наследуемся от BaseView

    public LoginPage(WebDriver driver) {// создаем конструктор
        super(driver);
    }
// ОПИСЫВАЕМ НЕОБХОДИМЫЕ ПОЛЯ ( В ИДЕАЛЕ ВСЕ,ЧТО ЕСТЬ НА СТРАНИЦЕ)
    @FindBy(id = "email")// поле емайл
    private WebElement emailField;

    @FindBy(id = "passwd")// поле пароль
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")// поле зарегестрироваться
    private WebElement submitButton;

    @Step("Login")// отобразит этап в Allure
    public MyAccountPage login(String login, String password) {// на вход передаем логин и пароль
        // метод для регистрации( включает все действия)- для негативных сценариев каждое действие в отдельный метод
        emailField.sendKeys(login);// вбиваем логин
        passwordField.sendKeys(password);//вбиваем пароль
        submitButton.click();//кликаем
        return new MyAccountPage(driver);
    }
}

