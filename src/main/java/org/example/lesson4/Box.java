package org.example.lesson4;

public class Box {
    private Integer ballsCount;// параметр-количесво мячей в коробке

    public Box() {
        ballsCount = 0;
    }//пустой конструктор, создает коробку, количество мячей всегда = 0

    public Integer getBallsCount() {
        return ballsCount;
    }//возвращает количество мячей

    public void addBall() {
        ballsCount ++;
    }// добавляет мяч в коробку

    public void deleteBall() throws BoxIsEmptyException {// удаляет мяч из коробки
        if (ballsCount == 0) {// отрабатывает случай если в коробке уже 0-удалять нельзя
            System.out.println("Коробка пустая");
            throw new BoxIsEmptyException();
        }
        ballsCount --;
    }
}
