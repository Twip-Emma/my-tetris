package org.example.test4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class Block {
    protected List<List<Point>> rotationStates; // 存储方块的旋转状态
    protected int rotationIndex; // 当前旋转状态的索引

    public abstract List<Point> getBlockCoordinates();

    public abstract void rotateClockwise();

    public abstract Color getColor();

    public Block cloneBlock() {
        try {
            Block clonedBlock = (Block) super.clone();
            clonedBlock.rotationIndex = this.rotationIndex;
            clonedBlock.rotationStates = new ArrayList<>(this.rotationStates.size());
            for (List<Point> state : this.rotationStates) {
                List<Point> newState = new ArrayList<>(state);
                clonedBlock.rotationStates.add(newState);
            }
            return clonedBlock;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}