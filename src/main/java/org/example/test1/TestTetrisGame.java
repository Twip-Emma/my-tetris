package org.example.test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class TestTetrisGame extends JFrame {

    private static final Random random = new Random();
    private JPanel blackSquare; // 黑色方块面板
    private int squareRow = 0, squareCol = 0; // 方块当前行和列
    JPanel panel = new JPanel(new GridLayout(20, 10));

    public TestTetrisGame() {
        setTitle("test1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600); // 根据格子大小和边框间隙计算窗口尺寸



        for (int i = 0; i < 10 * 20; i++) {
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(30, 30));
            if (i == 0) { // 创建并添加黑色方块
                blackSquare = new JPanel();
                blackSquare.setPreferredSize(new Dimension(30, 30));
                blackSquare.setBackground(Color.BLACK);
                cell.add(blackSquare);
            }
            panel.add(cell);
        }

        add(panel);

        // 添加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> moveUp();
                    case KeyEvent.VK_DOWN -> moveDown();
                    case KeyEvent.VK_LEFT -> moveLeft();
                    case KeyEvent.VK_RIGHT -> moveRight();
                }
                updatePosition(); // 更新方块位置
                revalidate(); // 重新验证布局
                repaint(); // 重绘界面
            }
        });

        setFocusable(true); // 设置窗口可获取焦点，以便接收键盘输入
        requestFocusInWindow(); // 请求焦点

        setVisible(true);
    }

    // 移动方块向左
    private void moveLeft() {
        if (squareCol > 0) {
            squareCol--;
        }
    }

    // 移动方块向右
    private void moveRight() {
        if (squareCol < 9) {
            squareCol++;
        }
    }

    private void moveUp() {
        if (squareRow > 0) {
            squareRow--;
        }
    }

    private void moveDown() {
        if (squareRow < 19) {
            squareRow++;
        }
    }

    // 更新方块的实际位置
    private void updatePosition() {
        panel.remove(blackSquare);

        // GridLayout布局下，需要确保移动到的是有效的格子位置
        if (squareCol >= 0 && squareCol < 10 && squareRow >= 0 && squareRow < 20) {
            JPanel targetCell = (JPanel) panel.getComponent(squareRow * 10 + squareCol);
            targetCell.add(blackSquare);
        }

        revalidate(); // 重新验证布局
        repaint(); // 重绘界面
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestTetrisGame::new);
    }
}
