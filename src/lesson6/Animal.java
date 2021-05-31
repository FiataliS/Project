package lesson6;

abstract class Animal {
    private static final int NOW_YEAR = 2021;
    private int wieght;
    private int birthYear;
    private String name;

    public static int count(){
        return Cat.count() + Dog.count();
    }

    public Animal() {
    }

    public Animal(String name, int birthYear, int wieght) {
        this.wieght = wieght;
        this.birthYear = birthYear;
        this.name = name;
    }

    int getWieght() {return wieght;}
    void setWieght(int wieght) {this.wieght = wieght;}
    int getAge() {return NOW_YEAR - birthYear;}
    void setAge(int age) {this.birthYear = age;}
    String getName() {return name;}
    void setName(String name) {this.name = name;}
}

