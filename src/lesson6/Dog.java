package lesson6;

class Dog extends Animal implements Running, Swimming {
    private static final int runMax = 500;
    private static final int swimMax = 10;

    public Dog() {}

    public Dog(String name, int birthYear, int wieght) {
        super(name, birthYear, wieght);
    }

    public static int count(){return Main.Dogs.length;}

    @Override
    public void iRun(int l) {
        super.iRun(l,runMax);
    }

    @Override
    public void iSwim(int l) {
        super.iSwim(l, swimMax);
    }
}
