package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    public static int turn = 1;

    public static char[][] field;
    public static int fieldSizeX;
    public static int fieldSizeY;
    public static int winLen;
    public static int mode;
    public static int cellHeight;
    public static int cellWidth;
    public static int cellX;
    public static int cellY;
    boolean isInitialized = false;

    static final char AI_DOT = '0';

    public Map() {
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e, getGraphics());
            }
        });
    }

    void update(MouseEvent e, Graphics g) {
        cellX = e.getX() / cellWidth;
        cellY = e.getY() / cellHeight;
        GameWindow.setJlabel(" ");
        if (turn == 1 ){
            if (TicTacToeCore.isCellValid(cellY, cellX)){
                field[cellY][cellX] = TicTacToeCore.PLAYER_DOT;
                setX(g);
                TicTacToeCore.games();
                if (turn==3){setWin(g);}
            }
            if(turn == 2 && StartNewGameWindow.gameMode == 0){
                    TicTacToeCore.aiStep();
                    setZero(g);
                    Map.turn = 1;
                    if (TicTacToeCore.checkWin(AI_DOT)) {
                        GameWindow.setJlabel("К сожалению Вы проиграли компьютеру");
                        Map.turn = 3;
                        if (turn==3){setWin(g);}
                    } else if (Map.isFieldFull()) {
                        GameWindow.setJlabel("Ничья");
                        Map.turn = 3;
                    }
            }
        } else if(turn == 2 && StartNewGameWindow.gameMode == 1){
            if (TicTacToeCore.isCellValid(cellY, cellX)){
                field[cellY][cellX] = TicTacToeCore.AI_DOT;
                setZero(g);
                TicTacToeCore.games();
                if (turn==3){setWin(g);}

            }
        }
    }

    void setZero (Graphics g){
        g.setColor(Color.BLUE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(cellWidth * cellX+10, cellHeight * cellY+ 10, cellWidth -20, cellHeight-20);
    }
    void setX(Graphics g){
        g.setColor(Color.GREEN);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(cellWidth * cellX + 15,cellHeight * cellY + 15,cellWidth * cellX+cellWidth - 15, cellHeight * cellY + cellHeight - 15);
        g2.drawLine(cellWidth * cellX + cellWidth - 15, cellHeight*cellY + 15, cellWidth * cellX + 15,cellHeight * cellY + cellHeight - 15);
    }
    void setWin(Graphics g){

        g.setColor(Color.red);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(cellWidth * TicTacToeCore.xWinIn+(cellWidth/2),cellHeight * TicTacToeCore.yWinIn+(cellHeight/2),
                cellWidth * TicTacToeCore.xWinOut+(cellWidth/2),cellHeight* TicTacToeCore.yWinOut+(cellHeight/2));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLength;
        if (mode==0){
            GameWindow.setJlabel("Это игра с компьютером, Вы ходите первым");
        } else {
            GameWindow.setJlabel("Это игра на двоих, ходит игрок 1");
        }
        TicTacToeCore.chips = winLength;
        TicTacToeCore.SIZE_X = fieldSizeX;
        TicTacToeCore.SIZE_Y = fieldSizeY;
        field = new char[fieldSizeY][fieldSizeX];
        this.mode = mode;
        initField();
        isInitialized = true;
        turn = 1;
        repaint();
    }

    void render(Graphics g) {
        if (!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0,x,panelHeight);
        }
    }

    void initField() {
        for(int i = 0; i < fieldSizeY; ++i) {
            for(int j = 0; j < fieldSizeX; ++j) {
                field[i][j] = TicTacToeCore.EMPTY_DOT;
            }
        }

    }

    static boolean isFieldFull() {
        for(int i = 0; i < Map.fieldSizeX; ++i) {
            for(int j = 0; j < Map.fieldSizeY; ++j) {
                if (Map.field[j][i] == TicTacToeCore.EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

}
