package lesson3;

import java.util.Random;
import java.util.Scanner;

public class Lesson3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите 1 для запуска задания 1 \nВведите 2 для запуска задания 2 \nВведите 3 для запуска задания 3");
        switch (sc.nextInt()) {
            case (1):
                randomGame();
                break;
            case (2):
                guessTheWords();
                break;
            case (3):
                task3();
                break;
            default:

        }
    }

//        1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю
//        дается 3 попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли
//        указанное пользователем число чем загаданное, или меньше. После победы или проигрыша выводится
//        запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    static void randomGame() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int number = random.nextInt(10);
        int life = 3;
        while (life > 0) {
            System.out.println("Угадайте число от 0 до 9, у Вас " + life + " попытки");
            int option = sc.nextInt();
            if (option == number || life == 1) {
                System.out.println((option == number ? "Вы угадали! \nПовторить игру еще раз? 1 – да / 0 – нет" :
                        "К сожалению вы проиграли! \nЗагаданное число было " + number +
                                "\nПовторить игру еще раз? 1 – да / 0 – нет"));
                if (sc.nextInt() == 1) {
                    life = 4;
                    number = random.nextInt(10);
                } else {
                    break;
                }
            } else if (option < number) {
                System.out.println("Загаданное число больше \nПопробуйте еще раз!");
            } else {
                System.out.println("Загаданное число меньше \nПопробуйте еще раз!");
            }
            life--;
        }
    }

//        2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot",
//        "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
//        "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
//        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
//        Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
//        apple – загаданное apricot - ответ игрока
//        ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//        Для сравнения двух слов посимвольно, можно пользоваться:
//        String str = "apple";
//        str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
//        Играем до тех пор, пока игрок не отгадает слово
//        Используем только маленькие буквы

    static void guessTheWords() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        String hiddenWord = words[random.nextInt(words.length)];
        System.out.println("Отгадай слово");
        while (true) {
            String suggestedWord = sc.next();
            if (comparison(hiddenWord, suggestedWord) == true) {
                System.out.println("Вы угадали!!!");
                break;
            } else {
                System.out.println("Вы неугадали, \nсовпали буквы: " + Coincidences(hiddenWord, suggestedWord) + ", " +
                        "попробуйте еще раз!");
            }
        }
    }

    static boolean comparison(String hiddenWord, String suggestedWord) { //Угадал, не угадал
        boolean equally = false;
        int letters = 0;
        if (hiddenWord.length() == suggestedWord.length()) {
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (hiddenWord.charAt(i) == suggestedWord.charAt(i)) {
                    letters++;
                    equally = (letters == hiddenWord.length() ? true : false);
                }
            }
        }
        return equally;
    }

    static StringBuilder Coincidences(String hiddenWord, String suggestedWord) { //подсказка
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            if (hiddenWord.length() > i && suggestedWord.length() > i ){
                if (hiddenWord.charAt(i) == suggestedWord.charAt(i)) {
                    sB.insert(i, suggestedWord.charAt(i));
                } else {sB.insert(i, "#");}
            } else {sB.insert(i, "#");}
        }
        return sB;
    }

//        3 Доделать задачу с лекции.
    static void task3(){
        String s = "Предложение      один    Теперь     предложение два         Предложение            три";
        String s1 = s.replaceAll(" +", " ");
        StringBuilder s2 = new StringBuilder(s1);

        for (int i = s2.length()-1; i > 1; i--) {
            if (s1.charAt(i) >= 'А' && s1.charAt(i) <= 'Я') {
                s2.insert( i - 1,".");
            } else if (s2.length() == i + 1 ){
                s2.insert( i + 1,".");
            }
        }
        System.out.println(s2);
    }
}
