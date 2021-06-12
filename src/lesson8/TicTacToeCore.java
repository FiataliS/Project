package lesson8;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeCore extends JPanel {

    static int SIZE_X;
    static int SIZE_Y;
    static int chips;
    static int aiX;
    static int aiY;

    static int xWinIn;
    static int yWinIn;
    static int xWinOut;
    static int yWinOut;

    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';
    static Scanner scanner;
    static final Random rand;

    static {
        scanner = new Scanner(System.in);
        rand = new Random();
    }

    public static boolean isCellValid(int y, int x) {
        if (x >= 0 && y >= 0 && x <= SIZE_X-1 && y <= SIZE_Y-1) {
            return Map.field[y][x] == EMPTY_DOT;
        } else {
            return false;
        }
    }

    public static void aiStep() {
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
        Map.cellX = aiX;
        Map.cellY = aiY;
    }

    static void setSym(int y, int x, char sym) {
        Map.field[y][x] = sym;

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
            if (Map.field[v][i] == EMPTY_DOT) {
                aiY = v;
                aiX = i;
                break;
            }
        }
    }
    private static void checkAiVertical(int v, int h) {
        for(int i = v; i < SIZE_Y; ++i) {
            if (Map.field[i][h] == EMPTY_DOT) {
                aiY = i;
                aiX = h;
                break;
            }
        }
    }
    private static void checkAiDioganalUp(int v, int h) {
        int i = 0;
        for(int j = 0; j < 4; ++j) {
            if (Map.field[v + i][h + j] == EMPTY_DOT) {
                aiY = v + i;
                aiX = h + j;
                break;
            }
            --i;
        }
    }

    private static void checkAiDioganalDown(int v, int h) {
        for(int i = 0; i < 4; ++i) {
            if (Map.field[i + v][i + h] == EMPTY_DOT) {
                aiY = i + v;
                aiX = i + h;
                break;
            }
        }
    }
///////////////////////////////////////////////

    public static boolean checkWin(char sym) {
        for(int v = 0; v < SIZE_Y; ++v) {
            yWinIn = v;

            for(int h = 0; h < SIZE_X; ++h) {
                xWinIn = h;
                yWinOut = v;
                if (h + chips <= SIZE_X) {
                    if (checkHorisontal(v, h, sym) == chips){
                        xWinOut = xWinIn + chips - 1;
                        return true;
                    }
                }
                if (v + chips <= SIZE_X ) {
                    if (checkVertical(v, h, sym) == chips) {
                        xWinOut=h;
                    return true;
                    }
                }
                if (v - chips >= -1 && h + chips <= SIZE_X) {if (checkDioganalUp(v, h, sym) == chips) {return true;}}

                if (v + chips <= SIZE_Y && h + chips <= SIZE_X) { if (checkDioganalDown(v, h, sym) == chips) {return true;}}
            }
        }
        return false;
    }

    private static int checkHorisontal(int v,int h, char sym) {
        int count = 0;
        for(int j = h; j < chips + h; ++j) {
            if ((sym == EMPTY_DOT ? Map.field[v][j] == PLAYER_DOT || Map.field[v][j] == AI_DOT : Map.field[v][j] == sym)) {
                ++count;
            }
        }

        return count;
    }
    private static int checkVertical(int v,int h, char sym) {
        int count = 0;
        for(int j = v; j < chips + v; ++j) {
            if ((sym == EMPTY_DOT ? Map.field[j][h] == PLAYER_DOT || Map.field[j][h] == AI_DOT : Map.field[j][h] == sym)) {
                ++count;
            }
            yWinOut = j;
        }
        return count;
    }
    private static int checkDioganalUp(int v,int h, char sym) {
        int count = 0;
        int i = 0;
        for(int j = 0; j < chips; ++j) {
            if ((sym == EMPTY_DOT ? Map.field[v + i][h + j] == PLAYER_DOT || Map.field[v + i][h + j] == AI_DOT : Map.field[v + i][h + j] == sym)) {
                ++count;
            }
            --i;
            xWinOut = h + j;
            yWinOut = v + i + 1;
        }
        return count;
    }

    private static int checkDioganalDown(int v,int h, char sym) {
        int count = 0;
        for(int i = 0; i < chips; ++i) {
            if ((sym == EMPTY_DOT ? Map.field[i + v][i + h] == PLAYER_DOT || Map.field[i + v][i + h] == AI_DOT : Map.field[i + v][i + h] == sym)) {
                ++count;
                xWinOut = i + h;
                yWinOut = i + v;
            }
        }
        return count;
    }

    public static void games() {
        if (StartNewGameWindow.gameMode == 0) {  //игра человек компутер
            Map.turn = 2;
            if (TicTacToeCore.checkWin(PLAYER_DOT)) {
                GameWindow.setJlabel("Вы выиграли");
                Map.turn = 3;
            } else if (Map.isFieldFull()) {
                GameWindow.setJlabel("Ничья");
                Map.turn = 4;
            }

        } else {                //игра на двоих

            switch (Map.turn) {
                case 1:
                    Map.turn = 2;
                    GameWindow.setJlabel("Ходит игрок 2");
                    if (TicTacToeCore.checkWin(PLAYER_DOT)) {
                        GameWindow.setJlabel("Победил игрок 1");
                        Map.turn = 3;
                    } else if (Map.isFieldFull()) {
                        GameWindow.setJlabel("Ничья");
                        Map.turn = 4;
                    }
                    break;
                case 2:
                    Map.turn = 1;
                    GameWindow.setJlabel("Ходит игрок 1");
                    if (TicTacToeCore.checkWin(AI_DOT)) {
                        Map.turn = 3;
                        GameWindow.setJlabel("Победил игрок 2");
                    } else if (Map.isFieldFull()) {
                        GameWindow.setJlabel("Ничья");
                        Map.turn = 4;
                    }
                    break;
            }

        }

    }
}
