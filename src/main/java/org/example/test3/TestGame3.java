package org.example.test3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestGame3 extends JFrame {

    private JPanel[][] grid; // 存储方块位置信息的二维数组
    private Piece piece; // 方块对象
    private JPanel panel = new JPanel(new GridLayout(20, 10));

    public TestGame3() {
        setTitle("test1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600); // 根据格子大小和边框间隙计算窗口尺寸

        grid = new JPanel[20][10];

        for (int i = 0; i < 10 * 20; i++) {
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(30, 30));
            panel.add(cell);
        }

        add(panel);

        // 初始化长条形方块位置信息
        grid[0][0] = (JPanel) panel.getComponent(0);
        grid[0][1] = (JPanel) panel.getComponent(1);
        grid[0][0].setBackground(Color.BLACK);
        grid[0][1].setBackground(Color.BLACK);

        // 创建方块对象
        piece = new Piece(grid, 0, 0);

        // 添加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> piece.moveUp();
                    case KeyEvent.VK_DOWN -> piece.moveDown();
                    case KeyEvent.VK_LEFT -> piece.moveLeft();
                    case KeyEvent.VK_RIGHT -> piece.moveRight();
                }
                piece.updatePosition(); // 更新方块位置
                revalidate(); // 重新验证布局
                repaint(); // 重绘界面
            }
        });

        setFocusable(true); // 设置窗口可获取焦点，以便接收键盘输入
        requestFocusInWindow(); // 请求焦点

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestGame3::new);
    }
}
