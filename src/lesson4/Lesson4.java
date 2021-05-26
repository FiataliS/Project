package lesson4;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    static final int XY = 5;
    static final int SIZE_X = XY;
    static final int SIZE_Y = XY;
    static final int chips = 4;
    static int aiX;
    static int aiY;
    static char[][] field = new char[SIZE_Y][SIZE_X];
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';
    static Scanner scanner;
    static final Random rand;


    static {
        scanner = new Scanner(System.in);
        rand = new Random();
    }

    public static void initField(int SIZE_X, int SIZE_Y) {
        for(int i = 0; i < SIZE_Y; ++i) {
            for(int j = 0; j < SIZE_X; ++j) {
                field[i][j] = EMPTY_DOT;
            }
        }

    }

    private static void printField() {
        numbering();
        for (int j = 0; j < SIZE_Y; j++) {
            System.out.print(j+1 + " ");
            for (int k = 0; k < SIZE_X; k++) {
                System.out.print("| " + field[j][k] + (k == SIZE_X - 1 ? " | " : " "));
            }
            System.out.print(j+1 + " ");
            System.out.println();
        }
        numbering();
    }
    private static void numbering(){
        System.out.print("    ");
        for (int l = 1; l < SIZE_X + 1; l++) {
            System.out.print(l + "   ");
        }
        System.out.println();
    }
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    private static boolean isCellValid(int y, int x) {
        if (x >= 0 && y >= 0 && x <= SIZE_X-1 && y <= SIZE_Y-1) {
            return field[y][x] == EMPTY_DOT;
        } else {
            return false;
        }
    }

    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты № столбца (1-"+ SIZE_X +")");
            x = scanner.nextInt() - 1;
            System.out.println("Введите координаты № строки (1-"+ SIZE_Y +")");
            y = scanner.nextInt() - 1;
        } while(!isCellValid(y, x));

        setSym(y, x, PLAYER_DOT);
    }

    private static void aiStep() {
        do {
            if (checkAI(PLAYER_DOT)) { //// защита
                continue;
            } else if (checkAI(AI_DOT)) { //// атака
                continue;
            } else {
                aiX = rand.nextInt(SIZE_X);
                aiY = rand.nextInt(SIZE_Y);
            }
        } while (!isCellValid(aiY, aiX));
        setSym(aiY, aiX, AI_DOT);
    }

    ///////////////////////////////////////////////проверки
    private static boolean checkAI(char sym) {
        for(int v = 0; v < SIZE_Y; ++v) {
            for(int h = 0; h < SIZE_X; ++h) {
                if (h + chips <= SIZE_X){
                    if (h + chips <= SIZE_X && checkHorisontal(v, h, sym) >= 3 && checkHorisontal(v,h,EMPTY_DOT) < 4) {
                        checkAiHoricontal(v,h);
                        return true;
                    }
                    if (v - chips > -2 && checkDioganalUp(v, h, sym) >= 3 && checkDioganalUp(v,h,EMPTY_DOT) < 4 ) {
                        checkAiDioganalUp(v,h);
                        return true;
                    }
                    if (v + chips <= SIZE_X && checkDioganalDown(v, h, sym) >= 3 && checkDioganalDown(v,h,EMPTY_DOT) < 4 ) {
                        checkAiDioganalDown(v,h);
                        return true;
                    }
                }
                if (v + chips <= SIZE_X && checkVertical(v, h, sym) >= 3 && checkVertical(v,h,EMPTY_DOT) < 4) {
                    checkAiVertical(v,h);
                    return true;
                }
            }
        }
        return false;
    }
    private static void checkAiHoricontal(int v, int h){
        for(int i = h; i < SIZE_X; ++i) {
            if (field[v][i] == EMPTY_DOT) {
                aiY = v;
                aiX = i;
                break;
            }
        }
    }
    private static void checkAiVertical(int v, int h) {
        for(int i = v; i < SIZE_Y; ++i) {
            if (field[i][h] == EMPTY_DOT) {
                aiY = i;
                aiX = h;
                break;
            }
        }
    }
    private static void checkAiDioganalUp(int v, int h) {
        int i = 0;
        for(int j = 0; j < 4; ++j) {
            if (field[v + i][h + j] == EMPTY_DOT) {
                aiY = v + i;
                aiX = h + j;
                break;
            }
            --i;
        }
    }

    private static void checkAiDioganalDown(int v, int h) {
        for(int i = 0; i < 4; ++i) {
            if (field[i + v][i + h] == EMPTY_DOT) {
                aiY = i + v;
                aiX = i + h;
                break;
            }
        }
    }
///////////////////////////////////////////////

    private static boolean isFieldFull() {
        for(int i = 0; i < SIZE_X; ++i) {
            for(int j = 0; j < SIZE_Y; ++j) {
                if (field[j][i] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char sym) {
        for(int v = 0; v < SIZE_Y; ++v) {
            for(int h = 0; h < SIZE_X; ++h) {
                if (h + chips <= SIZE_X) { if (checkHorisontal(v, h, sym) == chips){return true;}}
                if (v + chips <= SIZE_X ) {if (checkVertical(v, h, sym) == chips) {return true;}}
                if (v - chips >= -1 && h + chips <= SIZE_X) { if (checkDioganalUp(v, h, sym) == chips) {return true;}}
                if (v + chips <= SIZE_Y && h + chips <= SIZE_X) { if (checkDioganalDown(v, h, sym) == chips) {return true;}}
            }
        }
        return false;
    }

    private static int checkHorisontal(int v,int h, char sym) {
        int count = 0;
        for(int j = h; j < chips + h; ++j) {
            if ((sym == EMPTY_DOT ? field[v][j] == PLAYER_DOT || field[v][j] == AI_DOT : field[v][j] == sym)) {
                ++count;
            }
        }
        return count;
    }
    private static int checkVertical(int v,int h, char sym) {
        int count = 0;
        for(int j = v; j < chips + v; ++j) {
            if ((sym == EMPTY_DOT ? field[j][h] == PLAYER_DOT || field[j][h] == AI_DOT : field[j][h] == sym)) {
                ++count;
            }
        }
        return count;
    }
    private static int checkDioganalUp(int v,int h, char sym) {
        int count = 0;
        int i = 0;
        for(int j = 0; j < chips; ++j) {
            if ((sym == EMPTY_DOT ? field[v + i][h + j] == PLAYER_DOT || field[v + i][h + j] == AI_DOT : field[v + i][h + j] == sym)) {
                ++count;
            }
            --i;
        }
        return count;
    }

    private static int checkDioganalDown(int v,int h, char sym) {
        int count = 0;
        for(int i = 0; i < chips; ++i) {
            if ((sym == EMPTY_DOT ? field[i + v][i + h] == PLAYER_DOT || field[i + v][i + h] == AI_DOT : field[i + v][i + h] == sym)) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        initField(SIZE_X, SIZE_Y);
        printField();
        while(true) {
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }

            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("Win SkyNet!");
                break;
            }

            if (isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }


    }

}