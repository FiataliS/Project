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
        if ( l <= runMax){
            System.out.println(super.getName() + " пробежал " + l + "м");
        } else {
            System.out.println(super.getName() + " не смог пробежать больш " + runMax + "м");
        }
    }

    @Override
    public void iSwim(int l) {
        if ( l <= swimMax){
            System.out.println(super.getName() + " проплыл " + l + "м");
        } else {
            System.out.println(super.getName() + " утонул от изнурительного плавания после " + swimMax + "м");
        }
    }
}
