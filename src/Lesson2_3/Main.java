package Lesson2_3;

import java.util.*;

public class Main {
    static Random random = new Random();
    static List<String> words = new ArrayList<>();
    static int caunt = 0;

    public static void add(String surnames, String num){
        Phonebook.add(surnames,num);
    }
    public static void get(String surnames){
        Phonebook.get(surnames);
    }

//    1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//    Посчитать сколько раз встречается каждое слово.

    public static void addWords(){  //наполняем массив словами рандомно.
        String[] arrWords = {"Мясо","Мыло","Шило","Шапка","Метла","Кроватка", "Котлетка", "Помидор", "Стол", "Стул"};
        for (int i = 0; i < 20; i++) {
            words.add(arrWords[random.nextInt(arrWords.length)]);
        }
    }

    public static HashMap cauntWord(){ //счет слов
        HashMap<String, Integer> arrCaunt = new HashMap();
        for (int i = 0; i < words.size(); i++) {
            String res = words.get(i);
            Integer current = arrCaunt.get(res);
            arrCaunt.put(res, current == null ? 1 : current + 1 );
        }
        return arrCaunt;
    }

    public static void arrayListInit(){  //Добавление фамилий и номеров
        String[] surnames = {"Иванов","Петров","Сидоров","Николаев","Бенедиктова","Трусенкова"};
        for (int i = 0; i < surnames.length; i++) {
            caunt ++;
            List<String> numbers = new ArrayList<>();
            int sumOfNumbers = random.nextInt(4)+1;
            for (int j = 0; j < sumOfNumbers; j++) {
                String phoneNumber = "+79" +  random.nextInt(100000000)+1;
                numbers.add(phoneNumber);
            }
            new Phonebook(surnames[i], numbers);
        }

    }

    public static void main(String[] args) {
        //////////// задание 1
        addWords();
        cauntWord();
        System.out.println(words);
        System.out.println(cauntWord());

        //////////// задание 2
        arrayListInit();

        Phonebook.inf();
        add("Петров", "+79999999999");
        get("Петров");
        add("Петров", "+79999999999");
        get("Петров");
        add("Лисовский", "+7999999123");
        get("Лисовский");
        add("Лисовский", "+7999912331");
        get("Лисовский");
    }
}
