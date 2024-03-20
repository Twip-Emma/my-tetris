package org.example.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("爷的俄罗斯方块");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 660);

        TetrisPanel panel = new TetrisPanel();
        frame.add(panel);

        frame.setVisible(true);

        panel.startGame();
    }
}


