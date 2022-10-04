package org.example.lesson6.homeWork6;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TranslatePage extends BaseView {


    public TranslatePage(WebDriver driver) throws InterruptedException {
        super(driver);
    }

    @FindBy(id = "copyButton")// создаем WebElement
    private WebElement copyButton ;

    @FindBy(xpath = "//div[@id=\"dictionary\"]//span[text()=\"do their homework\"]")
    private WebElement translateVariantDoTheirHomeWork;


    @FindBy(xpath = "//button[text()=\"do their homework\"]")
    private WebElement exampleVariantDoTheirHomeWork;

    @FindBy(xpath = " //div[@id=\"textbox\" and @lang =\"en\"]")
    private WebElement changedTextField;

    //div[@class="dict-title"]/span[.="do their homework"]

    @FindBy(xpath = "//div[@class=\"tabs-container\"]//button[@class=\"tab tab_selected\" and text()=\"do their homework\"]")
    private WebElement tabTabSelected;

    @FindBy(id = "swapButton")
    private WebElement swapButton;

    @FindBy(id = "srcLangButton")
    private WebElement srcLangButton;

    @FindBy(xpath = "//button[@id=\"srcLangButton\" and text()=\"Английский\"]")
    private WebElement srcLangButtonEnglish;

    @FindBy(xpath = "//div[@class=\"langs-column langs-column_count_5\"]")
    private WebElement languageList;

    @FindBy(xpath = "//div[@class=\"langs-column langs-column_count_5\"]//div[text()=\"Английский\"]")
    private WebElement englishLanguage;

    @FindBy(xpath = "//div[@id=\"speller\"]/span")
    private WebElement wordSelected;

    @FindBy(xpath = "//div[@class=\"dict-meanings-value\"]/span[.=\"do\"]")
    private WebElement wordDo;

    @FindBy(xpath = "//span[@class =\"translation-word translation-chunk\" and .=\"doing\"]")
    private WebElement wordDoing;

    @FindBy(xpath = "//div[@class=\"textinput textlayer textlayer_src textlayer_measurer textlayer_measurer_alignment\"]")
    private WebElement changedElement;

    @FindBy(xpath = "//div[@class=\"listbox-option state-unselectable\" and @data-value=\"do\" ]")
    private WebElement variantOfTranslateDo;

    @FindBy(xpath = "//pre[@id=\"translation\" ]//span[.=\"do\"]")
    private WebElement changedSpan;

    @Step("проверяем изменения на странице- наличие элемента copyButton на странице")
    public TranslatePage checkSendText(){
        //Assertions: при переводе появляются дополнительные элементы в поле с переводом текста, поэтому проверка на их наличие, но в начале дожидаемся их появления
        webDriverWait.until(ExpectedConditions.visibilityOf(copyButton));// ожидаем появления элемента на странице
        Assertions.assertTrue(copyButton.isEnabled());
        return this;
    }

    @Step("Кликаем на вариант 'do their homework' в разделе 'Словарь'")
    public TranslatePage selectValueInDictionary() {
        translateVariantDoTheirHomeWork.click();
        return this;
    }

    @Step("Проверяем изменение url после ввода данных в поле 'Исходный текст'")
    public void checkSelectValueInDictionary(){
        // дожидаемся изменений на странице- для поля ввода текста изменится язык
        webDriverWait.until(ExpectedConditions.visibilityOf(changedTextField));
        //Assertions:  проверяем что содержание url изменилось
        Assertions.assertTrue(driver.getCurrentUrl().contains("text=do%20their%20homework"));
    }

    @Step("Кликаем на вариант 'do their homework' в разделе 'Примеры использования'")
    public TranslatePage selectValueInExamples() {
        exampleVariantDoTheirHomeWork.click();
        return this;
    }

    @Step("проверяем изменения на странице-измененный класс элемента после клика на элемент ")
    public void  checkSelectValueInExamples(){
        // дожидаемся изменений на странице
        webDriverWait.until(ExpectedConditions.visibilityOf(tabTabSelected));
        //Assertions:  проверяем что класс элемента изменился
        Assertions.assertTrue(tabTabSelected.isEnabled());
    }

    @Step("Кликаем на Swapbutton ")
    public TranslatePage selectLanguageUsingSwapbutton() {
        swapButton.click();
        // дожидаемся пока отобразятся изменения ( меняется название кнопки srcLangButton)
        return this;
    }

    @Step("Проверяем изменение url после изменения языка")
    public void checkSelectLanguageUsingSwapbutton(){
        // дожидаемся пока отобразятся изменения ( меняется название кнопки srcLangButton)
        webDriverWait.until(ExpectedConditions.visibilityOf(srcLangButtonEnglish));
        //Assertions: проверяем что содержание url изменилось(с ru-en на en-ru)
        Assertions.assertTrue(driver.getCurrentUrl().contains("lang=en-ru"));
    }

    @Step("Кликаем на scrLangButton (Английский), в появляюземся окне выбираем Английсекий язык ")
    public TranslatePage selectLanguageInLanguageList() {
        srcLangButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(languageList));
        englishLanguage.click();
        return this;
    }

    @Step("Проверяем изменение url после изменения языка")
    public void checkSelectLanguageInLanguageList(){
        //дожидаемся изменений на странице
        webDriverWait.until(ExpectedConditions.visibilityOf(srcLangButtonEnglish));
        //Assertions: проверяем что содержание url изменилось(с ru-en на en-ru)
        Assertions.assertTrue(driver.getCurrentUrl().contains("lang=en-ru"));
    }

    @Step("Выделяем конкретное слово в поле 'Исходный текст', в появляющемся окне выбираем вариант 'do'")
    public TranslatePage selectTranslateVariantOfSingleWorldInLeftPart() {
        actions.moveToElement(wordSelected, -140, 0)
                // переходим на элемент по локатору и задаем точное положение курсора по осям x и y (в данном случае передвигаемся на начало текста влево)
                .clickAndHold()// кликаем и удерживаем
                .moveByOffset(70, 0)// задаем смешение курсора( в данном случае вправо на одно слово)
                .release()// отпускаем мышь
                .perform();
        webDriverWait.until(ExpectedConditions.visibilityOf(wordDo));// дожидаемся появления элемента на странице
        wordDo.click();//кликаем

        return this;

    }

    @Step("проверяем,  в url теперь содержится текст 'do'")
    public void checkSelectTranslateVariantOfSingleWorldInLeftPart(){
        // Assertion: проверим, что содержимое страницы изменилось и в url теперь содержится текст "do"
        Assertions.assertTrue(driver.getCurrentUrl().contains("text=do"));
    }

    @Step("Наводим курсор мыши на слово doing в поле с переводом 'перевод' ")
    public TranslatePage highlightingCorrespondingWord() {
        actions.moveToElement(wordDoing)
                //наводим мышь на элемент ( находим его по локатору)
                .perform();
        return this;
    }

    @Step("проверяем, что в поле 'Исходный текст' выделилось соответствующее выбранному слово- проверяем отображение измененного класса на странице")
    public void checkHighlightingCorrespondingWord(){
        // Assertion: т.к. при наведении на элемент меняется название класса,в котором этот элемент находится, в проверке мы проверим есть ли измененное название класса на странице
        Assertions.assertTrue(changedElement.isEnabled());
    }

    @Step("наводим курсор мыши на конкретное слово, кликаем, в появившемся окошке кликаем 'do'")
    public TranslatePage selectTranslateValueInRightPart(){
    actions.moveToElement(wordDoing)
            //наводим мышь на элемент- конкретное слово ( находим его по локатору)
            .click()//кликаем на элемент
            .perform();
    // дожидаемся появления доп окошка
    webDriverWait.until(ExpectedConditions.visibilityOf(variantOfTranslateDo));// дожидаемся появления элемента на странице
    variantOfTranslateDo.click();// находим жлемент по локатору и кликаем
        return this;
}

    @Step("проверяем изменения на странице- отображение определенного span после выбора альтернативного перевода")
    public void checkSelectTranslateValueInRightPart(){
    // Assertion: т.к. при выборе альтернативного перевода из списка появляется определенный span, то в проверке проверим если ли этот span на странице
       // Assertions.assertTrue(changedSpan.isEnabled());//валидная проверка
        Assertions.assertTrue(languageList.isEnabled());//указан неверный элемент для проверки- чтобы заведомо поломать тест и проверить JunitExtension

}



}
