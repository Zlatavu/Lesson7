package org.example.lesson6;


import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BaseView {// наследуется от BaseView
    public NavigationBlock navigationBlock;// объявляея поле с блоком мы говорим о том, что этот блок присутствиет на этой странице

    public MyAccountPage(WebDriver driver) {// конструктор для создания страницы MyAccountPage
        super(driver);
        navigationBlock = new NavigationBlock(driver);// вызываем конструктор и инициализируем навигейшн блок, так как он на этой странице
    }
}

