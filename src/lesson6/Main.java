package lesson6;

public class Main {
// Также идут сомнения что я правильно обращаюсь к объектам класса Dog,Cat, в ДЗ написано
// (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.'); где до -> это запрос, а дальше ответ консоли.
// И dogBobik.run(150) не понятно как написать класс с именем объекта класса. Или это просто формальность?
    static Dog[] Dogs = {
            new Dog("Вася", 20018, 3),
            new Dog("Бобик", 2012, 20),
            new Dog("Мухтар", 2015, 45),
            new Dog("Татошка", 2005, 13),
    };
    static Cat[] Cats = {
            new Cat("Барсик", 2011, 3),
            new Cat("Васька", 2018, 2),
            new Cat("Муська", 2019, 1),
            new Cat("Снежок", 2013, 5),
    };

    public static void main(String[] args) {
        Cats[1].iRun(150);
        Dogs[3].iRun(450);
        Cats[1].iSwim(5);
        Dogs[3].iSwim(10);
        Dogs[0].iSwim(20);

        System.out.println("Животные: " + Animal.count() + "\nСобаки: " + Dog.count() + "\nКоты: " + Cat.count());
    }
}
