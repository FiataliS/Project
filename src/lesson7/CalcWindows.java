package lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcWindows extends JFrame {

    public CalcWindows() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400,500, 200,160);
        setTitle("Калькулятор");

        JPanel panelLabel = new JPanel(new GridLayout(3,2));
        JPanel panelRez = new JPanel(new GridLayout(2,2));
        JPanel panelAll = new JPanel(new GridLayout(2,1));

        JLabel textLabel1 = new JLabel("Значение 1");
        JLabel textLabel2 = new JLabel("Значение 2");
        JLabel textLabel3 = new JLabel("Результат");

        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();

        panelLabel.add(textLabel1);
        panelLabel.add(textField1);
        panelLabel.add(textLabel2);
        panelLabel.add(textField2);
        panelLabel.add(textLabel3);
        panelLabel.add(textField3);

        JButton button1 = new JButton("+");
        JButton button2 = new JButton("-");
        JButton button3 = new JButton("/");
        JButton button4 = new JButton("*");

        panelRez.add(button1);
        panelRez.add(button2);
        panelRez.add(button3);
        panelRez.add(button4);

        panelAll.add(panelLabel);
        panelAll.add(panelRez);
        add(panelAll, BorderLayout.SOUTH);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setText(String.valueOf(Float.parseFloat(textField1.getText()) + Float.parseFloat(textField2.getText())));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setText(String.valueOf(Float.parseFloat(textField1.getText()) - Float.parseFloat(textField2.getText())));
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setText(String.valueOf(Float.parseFloat(textField1.getText()) * Float.parseFloat(textField2.getText())));
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setText(String.valueOf(Float.parseFloat(textField1.getText()) / Float.parseFloat(textField2.getText())));
            }
        });
        setVisible(true);
    }
}
