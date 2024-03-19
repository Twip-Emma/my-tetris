package org.example.test2;

import javax.swing.*;
import java.awt.*;

public class LongBlackSquare extends AbstractSquare {
    private static final int SQUARE_WIDTH = 30;
    private JPanel topHalf, bottomHalf;

    public LongBlackSquare(int row, int col) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // 创建上下两半部分
        topHalf = new JPanel();
        topHalf.setPreferredSize(new Dimension(30, 30));
        topHalf.setBackground(Color.BLACK);

        bottomHalf = new JPanel();
        bottomHalf.setPreferredSize(new Dimension(30, 30));
        bottomHalf.setBackground(Color.BLACK);

        add(topHalf, BorderLayout.NORTH);
        add(bottomHalf, BorderLayout.SOUTH);

        this.row = row;
        this.col = col;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    // 移动方块向左（由于是1x2，所以移动要考虑占用两列）
    @Override
    public void moveLeft(int maxCol) {
        if (col > 0 && col <= maxCol - 2) {
            col--;
        }
    }

    // 移动方块向右
    @Override
    public void moveRight(int maxCol) {
        if (col >= 2 && col < maxCol - 1) {
            col++;
        }
    }

    // 移动方块向上
    @Override
    public void moveUp(int maxRow) {
        if (row > 0) {
            row--;
        }
    }

    // 移动方块向下
    @Override
    public void moveDown(int maxRow) {
        if (row < maxRow - 1) {
            row++;
        }
    }

    // 更新方块的实际位置到指定容器内
    @Override
    public void updatePosition(Container targetPanel) {
        Container currentParent = getParent();
        if (currentParent != null) {
            currentParent.remove(this);

            // 确保移动到的是有效的格子位置（考虑到长条形方块占据两列）
            if (col >= 0 && col + 1 < 10 && row >= 0 && row < 20) {
                JPanel targetCell1 = (JPanel) targetPanel.getComponent(row * 10 + col);
                JPanel targetCell2 = (JPanel) targetPanel.getComponent(row * 10 + col + 1);

                // 分别将长条形方块的上下两半添加到相邻的单元格中
                targetCell1.add(topHalf);
                targetCell2.add(bottomHalf);

                targetPanel.revalidate();
                targetPanel.repaint();
            }
        }
    }
}
