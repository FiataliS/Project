package Lesson3_1;

import java.util.Arrays;
import java.util.List;

public class Main {

    // Задача 1. Написать метод, который меняет два элемента массива местами.
    // (массив может быть любого ссылочного типа);
    private static <T> void swapElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //Задача 2. Написать метод, который преобразует массив в ArrayList
    private static <E> List<E> convertToList(E[] array) {
        return Arrays.asList(array);
    }

    public static void main(String[] args) {

    //Задача 3. a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
        // b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
        // поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        // c. Для хранения фруктов внутри коробки можете использовать ArrayList;
        // d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
        // (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
        // e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
        // подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками
        // мы можем сравнивать с коробками с апельсинами);
        // f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
        // (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно
        // в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
        // g. Не забываем про метод добавления фрукта в коробку.

        Box<Orange> orangeBox1 = new Box();
        Box<Orange> orangeBox2 = new Box();

        Box<Apple> appleBox1 = new Box();
        Box<Apple> appleBox2 = new Box();

        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());

        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());

        for (int i = 0; i < 10; i++) {
            orangeBox2.add(new Orange());
        }
        for (int i = 0; i < 15; i++) {
            appleBox2.add(new Apple());
        }


        orangeBox1.info();
        orangeBox2.info();
        appleBox1.info();
        appleBox2.info();

        System.out.println("Вес коробки 1 с апельсинами: " + orangeBox1.getWeight());
        System.out.println("Вес коробки 2 с апельсинами: " + orangeBox2.getWeight());
        System.out.println("Вес коробки 1 с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес коробки 2 с яблоками: " + appleBox2.getWeight());

        System.out.println("Сравнить вес orangeBox1 и appleBox1: " + !orangeBox1.compare(appleBox1));
        System.out.println("Сравнить вес orangeBox2 и appleBox2: " + !orangeBox2.compare(appleBox2));

        orangeBox2.moveAt(orangeBox1);
        //orangeBox1.moveAt(appleBox1);

        orangeBox1.info();
        orangeBox2.info();
        appleBox1.info();
        appleBox2.info();
    }
}

