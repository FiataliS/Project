package lesson6;

abstract class Animal implements Running, Swimming {
    private static final int NOW_YEAR = 2021;
    private int wieght;
    private int birthYear;
    private String name;

    public static int count(){
        return Cat.count() + Dog.count();
    }

    public Animal() {
    }

    public void iRun(int l, int runMax) {
        if ( l <= runMax){
            System.out.println(getName() + " пробежал " + l + "м");
        } else {
            System.out.println(getName() + " не смог пробежать больш " + runMax + "м");
        }
    }

    public void iSwim(int l, int swimMax) {
        if ( l <= swimMax){
            System.out.println(getName() + " проплыл " + l + "м");
        } else if ( swimMax == 0){
            System.out.println(getName() + ", не умеет плавать!");
        } else{
            System.out.println(getName() + " утонул от изнурительного плавания после " + swimMax + "м");
        }
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

