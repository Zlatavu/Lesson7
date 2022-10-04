package org.example.lesson4.homeWork4;

public class Triangle {
    // метод будет принимать на вход значения сторон
    public static double calcArea(double sideA, double sideB, double sideC) throws TriangleException {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {// условие-если стороны меньше нуля-это некорректное знавчение, обработаем исклбчением
            System.out.println("некорректное значение- сторона треугольника не может быть меньше нуля");
            throw new TriangleException();
        }
        // площадь треугольника будем считать исспользуя формулу Герона
        double pp = (sideA + sideB + sideC) / 2;// для этого необходимо вычислить полупериметр
        double area = Math.sqrt(pp * (pp - sideA) * (pp - sideB) * (pp - sideC));//высчитываем площадь по формуле
        System.out.println("Площадь треугольника равна" + area );
        return area;

    }

}
