package org.example.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//все тесты можно разделить на 2 классы- когда коробка пустая и когда в ней есть мячи
public class BoxTest {
    Box box;//создаем коробку

    @Nested// аннотация, позволяющая вложить класс в класс
    class WhenBoxIsEmpty {
        @BeforeEach// будет дейстовать для всех вложенных классов
        void createBox() {
            box = new Box();
        }// перед каждым тестом создаем пустую коробку

        @Test
        void exceptionWhenTryToDeleteBall() {// проверим что выкидывается исключение, если коробка пустая
            Assertions.assertThrows(BoxIsEmptyException.class, () -> box.deleteBall());
            // класс Assertions. метод assertThrows- работа с исключениями (класс Exception , код, который может вызвать это исключение
            assertThatExceptionOfType(BoxIsEmptyException.class).isThrownBy(() -> box.deleteBall());
            // тоже самое для assertJ
            // проверить Что тип исключения (соответствует этому классу). выкидывается в этом методе( )
        }

        @Nested// добавить еще вложенный класс для состояния, когда в коробке есть мяч
        class WhenOneBall {
            @BeforeEach// сработает для методов к этом вложенном классе
            void addBall() {
                box.addBall();
            }// перед каждым тестом всегда добавляется мяч

            @Test
            void deleteBallTest() throws BoxIsEmptyException {//
                box.deleteBall();// удаляем мяч
                assertThat(box.getBallsCount()).isEqualTo(0);
                // проверим что количесво мечей в коробке соответствует нулю
            }
        }
    }
}
