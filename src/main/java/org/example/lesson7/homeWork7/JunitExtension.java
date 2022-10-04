package org.example.lesson7.homeWork7;


import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;

public class JunitExtension implements TestWatcher {// имплементируем интерфейс, он выполняет действия относительно выполнения теста
    ByteArrayInputStream screenshot;

    public ByteArrayInputStream getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(ByteArrayInputStream screenshot) {
        this.screenshot = screenshot;
    }

    public void testFailed(ExtensionContext context, Throwable cause) {// если тест упал
        //
        Allure.addAttachment("Тест упал- Скриншот перед закрытие браузера", screenshot);
    }
}
