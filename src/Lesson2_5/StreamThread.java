package Lesson2_5;

class StreamThread extends Thread{
    float[] arr;
    float[] arrCalc;

    StreamThread(float[] arr) {this.arr = arr;}

    float[] getArr() {return arrCalc;}

    @Override
    public void run() {arrCalc = Main.calc(arr);}
}
