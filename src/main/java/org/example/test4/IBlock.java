package org.example.test4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IBlock extends Block {
    public IBlock() {
        // Define rotation states for I block
        rotationStates = new ArrayList<>();
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))); // State 0
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0))); // State 1
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))); // State 2
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0))); // State 3
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
}