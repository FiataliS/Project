package Lesson2_5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    private static  void arrInit (){for (int i = 0; i < arr.length; i++) {arr[i]=1;}}

    public static float[] calc(float[] arrCalc) {
        for (int i = 0; i < arrCalc.length; i++) {
            arrCalc[i] = (float) (arrCalc[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arrCalc;
    }

    private static long arrSing(){
        long a = System.currentTimeMillis();
        calc(arr);

        return System.currentTimeMillis() - a;
    }

    private static long arrMult(){
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        StreamThread t1 = new StreamThread(a1);
        StreamThread t2 = new StreamThread(a2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a1 = t1.getArr();
        a2 = t2.getArr();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, a1.length, a2.length);

        return System.currentTimeMillis() - a;
    }

    public static void main(String[] args) {
        arrInit();  // инициализация массива
        System.out.println("Время в один поток: " + arrSing()); // отрабатываем в один поток
        System.out.print("Время в два потока: " + arrMult());  // отрабатываем в два потока
    }
}
