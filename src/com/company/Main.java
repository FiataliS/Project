package com.company;

public class Main {
    //Задание 1
    public static void main(String[] args) {
    //
        // Задание 2
        byte b = 1;
        short s = 10000;
        int i = 5000000;
        long l = 88888888L;
        float f = 12.5f;
        double d = 12.5;
        boolean flag = true;
        char c = 't';
        String str = "Hello!";
        //
        //////////////////
        System.out.println(exercise3(5, 5, 10, 10)); //работа 3 задания
        System.out.println(exercise4(5, 16));              //работа 4 задания
        exercise5(1);                                         //работа 5 задания
        System.out.println(exercise6(1));                     //работа 6 задания
        exercise7("Иван");                                   //работа 7 задания
        leapYear(1041);                                          //работа 8 задания
        //////////////////
    }

    // Задание 3
    static int exercise3(int a, int b, int c, int d) {return a * (b + (c / d));}
    //

    // Задание 4
    static boolean exercise4(int a, int b) {
        if (a + b >= 10 && a + b <= 20) {
            return true;
        } else {
            return false;
        }
    }
    //

    // Задание 5
    static void exercise5(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }
    //

    // Задание 6
    static boolean exercise6(int a) {
        if (a < 0) {
            return true;
        } else {
            return false;
        }
    }
    //

    // Задание 7
    static void exercise7 (String str) {
        System.out.println("Привет, " + str);
    }
    //

    // Задание 8
    static void leapYear (int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 4 == 0 && year % 400 == 0){
            System.out.println("Год " +year + " высокостный");
        } else {
            System.out.println("Год " +year + " не высокосный");
        }
    }
    //

}
