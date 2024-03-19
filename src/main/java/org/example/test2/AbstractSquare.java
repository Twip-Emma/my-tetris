package org.example.test2;

import javax.swing.*;
import java.awt.*;

// 抽象方块类
public abstract class AbstractSquare extends JPanel {
    protected int row, col;

    public abstract void moveLeft(int maxCol);
    public abstract void moveRight(int maxCol);
    public abstract void moveUp(int maxRow);
    public abstract void moveDown(int maxRow);
    public abstract void updatePosition(Container targetPanel);
    // 添加获取方块尺寸的方法
    public abstract int getWidth();
    public abstract int getHeight();

}
