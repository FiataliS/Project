package lesson6;

class Cat extends Animal implements Running, Swimming {
    private static final int runMax = 200;
    private static final int swimMax = 0;

    public Cat() {}

    public Cat(String name, int birthYear, int wieght) {
        super(name, birthYear, wieght);
    }

    public static int count(){return Main.Cats.length;}

    @Override
    public void iRun(int l) {
        if ( l <= runMax){
            System.out.println(super.getName() + " пробежал " + l + "м");
        } else {
            System.out.println(super.getName() + " не смог пробежать больше " + runMax + "м");
        }
    }

    @Override
    public void iSwim(int l) {
            System.out.println("Кот " + super.getName() + ", не умеет плавать!");
    }
}
