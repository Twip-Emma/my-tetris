package org.example.test4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class OBlock extends Block {
    public OBlock() {
        // Define rotation states for O block (O block does not rotate)
        rotationStates = new ArrayList<>();
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))); // State 0
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))); // State 1
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))); // State 2
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))); // State 3
        rotationIndex = 0; // 初始旋转状态为 0
    }

    @Override
    public List<Point> getBlockCoordinates() {
        return rotationStates.get(rotationIndex);
    }

    @Override
    public void rotateClockwise() {
        // O block does not rotate, so no action needed
    }
}