package org.example.test3;

import javax.swing.*;
import java.awt.*;

public class Piece {
    private JPanel[][] grid;
    private int squareRow;
    private int squareCol;

    public Piece(JPanel[][] grid, int squareRow, int squareCol) {
        this.grid = grid;
        this.squareRow = squareRow;
        this.squareCol = squareCol;
    }

    // 判断方块是否可以向左移动
    public boolean canMoveLeft() {
        return squareCol > 0;
    }

    // 判断方块是否可以向右移动
    public boolean canMoveRight() {
        return squareCol < 9;
    }

    // 判断方块是否可以向上移动
    public boolean canMoveUp() {
        return squareRow > 0;
    }

    // 判断方块是否可以向下移动
    public boolean canMoveDown() {
        return squareRow < 18; // 因为是长条形方块，所以只需要检查下方是否有空间
    }

    // 移动方块向左
    public void moveLeft() {
        if (canMoveLeft()) {
            squareCol--;
        }
    }

    // 移动方块向右
    public void moveRight() {
        if (canMoveRight()) {
            squareCol++;
        }
    }

    // 移动方块向上
    public void moveUp() {
        if (canMoveUp()) {
            squareRow--;
        }
    }

    // 移动方块向下
    public void moveDown() {
        if (canMoveDown()) {
            squareRow++;
        }
    }

    // 更新方块的实际位置
    public void updatePosition() {
        // 恢复原来位置的背景颜色
        if (grid[squareRow][squareCol] != null && grid[squareRow][squareCol + 1] != null) {
            grid[squareRow][squareCol].setBackground(UIManager.getColor("Panel.background"));
            grid[squareRow][squareCol + 1].setBackground(UIManager.getColor("Panel.background"));
        }

        // 设置新位置的方块颜色
        if (squareCol >= 0 && squareCol + 1 < 10) {
            grid[squareRow][squareCol] = grid[squareRow][squareCol + 1] = (JPanel) grid[squareRow][squareCol].getParent().getComponent(squareRow * 10 + squareCol);
            grid[squareRow][squareCol].setBackground(Color.BLACK);
            grid[squareRow][squareCol + 1].setBackground(Color.BLACK);
        }
    }
}
