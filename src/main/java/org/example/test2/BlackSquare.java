package org.example.test2;

import javax.swing.*;
import java.awt.*;

// 1x1黑色方块类
public class BlackSquare extends AbstractSquare {

    public BlackSquare(int row, int col) {
        setPreferredSize(new Dimension(30, 30));
        setBackground(Color.BLACK);
        this.row = row;
        this.col = col;
    }

    // 默认情况下，宽度和高度都为1
    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public void moveLeft(int maxCol) {
        if (col > 0) {
            col--;
        }
    }

    @Override
    public void moveRight(int maxCol) {
        if (col < maxCol - 1) {
            col++;
        }
    }

    @Override
    public void moveUp(int maxRow) {
        if (row > 0) {
            row--;
        }
    }

    @Override
    public void moveDown(int maxRow) {
        if (row < maxRow - 1) {
            row++;
        }
    }

    @Override
    public void updatePosition(Container targetPanel) {
        Container currentParent = getParent();
        if (currentParent != null) {
            currentParent.remove(this);

            if (col >= 0 && col < 10 && row >= 0 && row < 20) {
                JPanel targetCell = (JPanel) targetPanel.getComponent(row * 10 + col);
                targetCell.add(this);
            }

            targetPanel.revalidate();
            targetPanel.repaint();
        }
    }
}
