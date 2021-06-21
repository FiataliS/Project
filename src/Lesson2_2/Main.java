package Lesson2_2;


import java.util.Random;

public class Main {
    static String arr[][] = new String[4][4];
////        1. Напишите метод, на вход которого подаётся двумерный строковый массив
////        размером 4х4, при подаче массива другого размера необходимо бросить исключение
////        MyArraySizeException.
////        2. Далее метод должен пройтись по всем элементам массива, преобразовать
////        в int, и просуммировать. Если в каком-то элементе массива преобразование не удалось
////        (например, в ячейке лежит символ или текст вместо числа), должно быть брошено
////        исключение MyArrayDataException, с детализацией в какой именно ячейке
////        лежат неверные данные.
////        3. В методе main() вызвать полученный метод, обработать возможные исключения
////        MySizeArrayException и MyArrayDataException, и вывести результат расчета.

    public static void arrProcessor(String arr[][]){
        try {
            SizeArrayException.getArr(arr);
            ArrayData.getArr(arr);
        } catch (MySizeArrayException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[][] arr = {{"3","3","3","1"},{"3","3","2","1"},{"3","3","4","1"},{"3","3","2","1"}};
        arrProcessor(arr);
    }
}
