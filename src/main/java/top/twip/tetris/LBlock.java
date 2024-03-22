package top.twip.tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LBlock extends Block {
    public LBlock() {
        // Define rotation states for L block
        rotationStates = new ArrayList<>();
        rotationStates.add(Arrays.asList(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0))); // State 0
        rotationStates.add(Arrays.asList(new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2))); // State 1
        rotationStates.add(Arrays.asList(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2))); // State 2
        rotationStates.add(Arrays.asList(new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0))); // State 3
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
        return Color.CYAN;
    }
}
