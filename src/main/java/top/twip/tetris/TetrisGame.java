package top.twip.tetris;

import javax.swing.*;

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


