package org.example.lesson4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {
    @Test
    void assertJTest() {
        //Assumptions.assumeTrue(1 == 2);// проверяет предусловие
        // нужны чтобы прерывать тест, если предусловие не отработало

        List<String> stringList = Arrays.asList("test1", "test3", "test5");//создаем список строк, чтобы потом передать его в assertions

        Assertions.assertAll(// позволяет прогнать все проверки, даже если один из них упал
                // - вызывает тесты независимо друг от друга
                () -> assertThat(new Functions().isPalindrome("123")).isFalse(),
                //проверить ЧТО (результат вызова метода isPalindrome). это false
                () ->  assertThat(5).isGreaterThan(4).isLessThan(3),
                //проверить ЧТО ( входные данные). больше чем (входные данные).
                () -> assertThat(stringList).containsAnyOf("test6", "test1")
                //проверить Что (созданный список строк). содержит что то из( входные данные)
        );//P.S. ()->- лямбда выражение
    }
}
