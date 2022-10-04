package org.example.lesson4;

public class Cat {// класс, в нем 2 параметра
    private String name;
    private int age;

    public Cat(String name, int age){//конструктор пустой чтобы создавать объект с 2 параметрами
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

