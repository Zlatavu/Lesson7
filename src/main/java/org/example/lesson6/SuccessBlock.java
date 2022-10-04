package org.example.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessBlock extends BaseView {
    public SuccessBlock(WebDriver driver) {//конструктор
        super(driver);
    }

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement totalSumma;

    private final static String iconOkXpathLocator = "//i[@class='icon-ok']";

    @Step("Проверяем итоговую сумму заказа")
    public void checkTotalSumma(String expextedSumma) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconOkXpathLocator)));
        Assertions.assertEquals(expextedSumma, totalSumma.getText());
    }
}
