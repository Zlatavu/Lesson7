package org.example.lesson6;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TShirtsPage extends BaseView {
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")// локатор, который находит список чего-то (в нашем случае размеры)
    private List<WebElement> sizesList;// передаем список элементов(список ращзмеров)

    @Step("Выбрать размер")
    public TShirtsPage selectSize(String size) {//метод для работы с фильтром размеров-на вход строку с размером
        // Allure.step("выбрать размер");// вызов команды напрямую через allure
        webDriverWait.until(d -> sizesList.size() > 1);//
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();// обращаемся к списку размеров и фильтруем его поимени размера/
        return this;
    }

    @FindBy(xpath = "//div[contains(@class,'slider')]//a[1]")
    private WebElement leftPriceSliderElement;

    @Step("Сдвинуть цену вправо")
    public TShirtsPage moveLeftPriceSliderElement(int pixelsCount) {
        actions.clickAndHold(leftPriceSliderElement)
                .moveByOffset(pixelsCount, 0)
                .perform();
        return this;
    }

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> dressesList;// находим элемент из списка

    private static final String addToCartButtonXpathLocator = "//span[.='Add to cart']";// объявляем локатор в переменной
    @FindBy(xpath = addToCartButtonXpathLocator)// в аннотации пользуемся переменной( в названии указывает тип локатора)
    private WebElement addToCartButton;

    @Step("Добавить товар в корзину по имени")
    public SuccessBlock addToCartByName(String tshirtName) throws InterruptedException {
        // webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));

        actions.moveToElement(dressesList.stream().filter(d -> d.getText().contains(tshirtName)).findFirst().get())
                .perform();
        dressesList.stream().filter(d -> d.getText().contains(tshirtName)).findFirst().get().findElement(
                By.xpath(addToCartButtonXpathLocator)).click();
        return new SuccessBlock(driver);
    }
}
