package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 507;
    private static final int WIN_POS_X = 800;
    private static final int WIN_POS_Y = 300;
    private static Map field;
    private static StartNewGameWindow startNewGameWindow;
    public static JLabel jLabel = new JLabel(" ");


    public GameWindow() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle("Крестики нолики");
        setResizable(false);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JPanel bottomPanel = new JPanel(new GridLayout(1,3));
        JButton btnNewGame = new JButton("Новая игра");
        JButton btnExit = new JButton("Выход");

        startNewGameWindow = new StartNewGameWindow(this);

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        field = new Map();
        add(jLabel,BorderLayout.NORTH);
        jLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        add(field, BorderLayout.CENTER);

        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnExit);
        add(jLabel, BorderLayout.NORTH);

        add(bottomPanel, BorderLayout.SOUTH);


        setVisible(true);

    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        field.startNewGame(mode, fieldSizeX, fieldSizeY, winLength);
    }
    static void setJlabel (String text){
        jLabel.setText(text);
    }
}
