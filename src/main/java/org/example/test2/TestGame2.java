package org.example.test2;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGame2 extends JFrame {
    private static final Random random = new Random();
    private JPanel panel;
    private GridLayout gridLayout;
    private List<AbstractSquare> squares = new ArrayList<>();
    private int currentSquareType = 0; // 用于记录当前显示的方块类型

    public TestGame2() {
        setTitle("test1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridLayout = new GridLayout(20, 10);
        panel = new JPanel(gridLayout);

        // 初始化两种类型的方块并放置到初始位置
        addSquare(new BlackSquare(0, 0));
        addSquare(new LongBlackSquare(1, 1));

        // 添加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // 按空格切换方块组合
                    // 切换方块类型
                    currentSquareType = (currentSquareType + 1) % 2;

                    // 清除当前显示的所有方块
                    for (AbstractSquare square : squares) {
                        panel.remove(square);
                    }
                    squares.clear();

                    // 根据当前方块类型添加新的方块到初始位置
                    switch (currentSquareType) {
                        case 0:
                            addSquare(new BlackSquare(0, 0));
                            break;
                        case 1:
                            addSquare(new LongBlackSquare(1, 1));
                            break;
                    }

                    revalidate();
                    repaint();
                } else {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> squares.forEach(square -> square.moveUp(20));
                        case KeyEvent.VK_DOWN -> squares.forEach(square -> square.moveDown(20));
                        case KeyEvent.VK_LEFT -> squares.forEach(square -> square.moveLeft(10));
                        case KeyEvent.VK_RIGHT -> squares.forEach(square -> square.moveRight(10));
                    }

                    // 更新每个方块的位置
                    squares.forEach(square -> square.updatePosition(panel));
                    revalidate();
                    repaint();
                }
            }
        });

        setSize(300, 600); // 根据格子大小和边框间隙计算窗口尺寸
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    // 添加任意形状的方块到指定位置
    private void addSquare(AbstractSquare square) {
        int row = square.row;
        int col = square.col;

        // 计算实际需要添加的单元格数量（根据方块形状）
        int width = square.getWidth();
        int height = square.getHeight();

        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(30, 30));

                if (r == row && c == col) {
                    // 添加整个方块到第一个单元格
                    cell.add(square);
                } else if (square instanceof LongBlackSquare && r == row && c == col + 1) {
                    // 对于1x2的长条形方块，第二个单元格添加其下半部分
//                    ((LongBlackSquare) square).addToCell(cell);
                }
                panel.add(cell);
            }
        }
        squares.add(square);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestGame2::new);
    }
}