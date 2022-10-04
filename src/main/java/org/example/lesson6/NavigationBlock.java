package org.example.lesson6;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
// для подторяющихся на каждой странице сайта блоки-элементы создаем отдельные блоки- и потом из них собираем страниццу
public class NavigationBlock extends BaseView {// наследуется от BaseView
    public NavigationBlock(WebDriver driver) {// создаем конструктор
        super(driver);
    }

    @FindBy(xpath = "//li/a[.='Women']")// ссылка Women
    private WebElement womenButton;

    @FindBy(xpath = "//ul[contains(@class, 'submenu')]//a[.='T-shirts']")// кнопки TShirts в выпадающем менюсюда защьем все что внутри подменю
    private WebElement tShirtsButtonInSubmenu;

    @Step("Навести курсор мыши на Women и кликнуть на раздел Рубашки")
    public TShirtsPage hoverWomenMenuAndClickTShirts() {// метод "наведем на меню и кликнет tshirts
        webDriverWait.until(ExpectedConditions.visibilityOf(womenButton));
        actions.moveToElement(womenButton)// наводим на элемент
                .perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tShirtsButtonInSubmenu));// дожидаемся пояаления элемента из выпадающего списка
        tShirtsButtonInSubmenu.click();
        return new TShirtsPage(driver);
    }
}
