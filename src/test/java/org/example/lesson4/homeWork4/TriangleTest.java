package org.example.lesson4.homeWork4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Test
    @DisplayName("Тест_1: проверка на корректность вычисления")
    void isCalculationCorrect () throws  TriangleException {//(double sideA, double sideB, double sideC)
        logger.debug("Тест_1 isCalculationCorrect is running");
    Assertions.assertEquals(1.7320508075688772, Triangle.calcArea(2,2,2));

    }
    @Test
    @DisplayName("Тест_2: правильность отработки исключения")
    void isExceptionCorrect(){
        logger.debug("Тест_2 isExceptionCorrect is running");
    Assertions.assertThrows(TriangleException.class, () -> Triangle.calcArea(2,1,0));
    }

    // попыталась парметризировать тест, единственно что пришло в голову, чтобы можно было передавать данные сразу трех сторон-
    // это передать объект- для этого сделал отдельный класс, которые делает треугольники-объекты

    @ParameterizedTest
    @DisplayName("Тест_3-4: Проверка на корректность исчесление в параметризированном тесте")
    @MethodSource( "triangleAndProvider")//источник данных-метод(его создали ниже)
    void triangleObjects(TriangleObject triangleObject, double expected) throws TriangleException {//в тест передакем объект - треугольник и ожидаемый результат- площадь треугольника
        logger.debug("Тест_3-4 triangleObjects is running");
        double result = triangleObject.calcTriangleArea();
        Assertions.assertEquals(result,expected);
        //Класс Assertions. метод assertEquals( фактическая площадь треугольника, ожидаемая площадь треугольника)
    }
    //  в качестве провайдера используем не готовую анннотацию, а метод который возвращает объект
    private static List<Arguments> triangleAndProvider(){// Arguments позволяет передавать массив из разнородных объектов
      logger.debug("создаем массив из треугольников + ожидаемый результат проверки");
        return Arrays.asList(
                Arguments.of(new TriangleObject(2,2,2),1.7320508075688772),
                Arguments.of(new TriangleObject(3,2,2),1.984313483298443)

        );
    }
    @ParameterizedTest
    @DisplayName("Тест_4-5: правильность отработки исключения в параметризированном тесте")
    @MethodSource( "triangleAndProvider2")//источник данных-метод(его создали ниже)
    void triangleObjectsException(TriangleObject triangleObject){
        logger.debug("Тест_4-5 triangleObjectsException is running");
        Assertions.assertThrows(TriangleException.class, () -> new TriangleObject(triangleObject.sideA, triangleObject.sideB, triangleObject.sideC).calcTriangleArea());
    }
    private static List<Arguments> triangleAndProvider2(){// Arguments позволяет передавать массив из разнородных объектов
        logger.debug("создаем массив из треугольников ");
        return Arrays.asList(
                Arguments.of(new TriangleObject(0,2,2)),
                Arguments.of(new TriangleObject(3,2,0))
        );
    }

}


