package org.example.test4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TBlock extends Block {
    public TBlock() {
        // Define rotation states for T block
        rotationStates = new ArrayList<>();
        rotationStates.add(Arrays.asList(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1))); // State 0
        rotationStates.add(Arrays.asList(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2))); // State 1
        rotationStates.add(Arrays.asList(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2))); // State 2
        rotationStates.add(Arrays.asList(new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2))); // State 3
        rotationIndex = 0; // 初始旋转状态为 0
    }

    @Override
    public List<Point> getBlockCoordinates() {
        return rotationStates.get(rotationIndex);
    }

    @Override
    public void rotateClockwise() {
        rotationIndex = (rotationIndex + 1) % rotationStates.size(); // 循环遍历旋转状态
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}