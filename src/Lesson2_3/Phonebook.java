package Lesson2_3;

import java.util.*;

public class Phonebook {

//    2. Написать простой класс ТелефонныйСправочник, который хранит в себе список
//    фамилий и телефонных номеров. В этот телефонный справочник с помощью метода
//    add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
//    Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе
//    такой фамилии должны выводиться все телефоны.
//    Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную
//    запись добавлять еще дополнительные поля (имя, отчество, адрес), делать взаимодействие
//    с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner),
//    тестировать просто из метода main() прописывая add() и get().

    static String surnames;
    static List<String> number = new ArrayList<>();
    static Hashtable<String, List<String>> arr = new Hashtable();

    public Phonebook(String surnames, List<String> number) {
        arr.put(this.surnames = surnames, this.number = number);
    }

    public static void add(String surnames, String num){
        List<String> numbers= new ArrayList<>();
        if (arr.containsKey(surnames)){
            numbers = arr.get(surnames);
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i)==num){
                    System.out.println("№ " + num + " Уже есть,и добавлен не будет!");
                    break;
                } else if(i==numbers.size()-1 && numbers.get(i)!=num){
                    numbers.add(num);
                    arr.replace(surnames,numbers);
                    break;
                }
            }
        } else  {
            numbers.add(num);
            new Phonebook(surnames,numbers);
        }
    }

    public static void get(String surnames){
        List<String> numbers;
        if (arr.containsKey(surnames)){
            numbers = arr.get(surnames);
            System.out.println(surnames + ":  " + numbers);
        } else{
            System.out.println("Абонент "+surnames+" не наеден" );
        }

    }
    public static void inf(){System.out.println(arr);}
}
