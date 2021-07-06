package Lesson2_6;

import java.io.PrintWriter;
import java.util.Scanner;

public class FlowOne extends Thread {
    PrintWriter out;
    Scanner in;
    boolean server = false;

    FlowOne(PrintWriter out, Scanner in, boolean server) {
        this.in = in;
        this.out = out;
        this.server=server;
    }

    @Override
    public void run() {
        one(in,out);
    }

    public void one (Scanner in, PrintWriter out){
        while (true) {
            String str = in.nextLine();
            if(str.equals("/end")) {
                out.println("/end");
                break;
            }
            System.out.println((server==false?"Server ":"Client ") + str);
        }
    }
}

