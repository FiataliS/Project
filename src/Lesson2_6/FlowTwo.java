package Lesson2_6;

import java.io.PrintWriter;
import java.util.Scanner;

public class FlowTwo extends Thread {

    PrintWriter out;
    Scanner console;

    FlowTwo(PrintWriter out, Scanner console) {
      this.out = out;
      this.console = console;
     }

    @Override
    public void run() {
        two(out, console);
    }

    public void two(PrintWriter out, Scanner console) {
        while (true) {
            System.out.println("Введите сообщение");
            String str = console.nextLine();
            System.out.println("Сообщение отправлено!");
            out.println(str);
        }
    }
}