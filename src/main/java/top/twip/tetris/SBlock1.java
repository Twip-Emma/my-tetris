package top.twip.tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SBlock1 extends Block{

    public SBlock1() {
        rotationStates = new ArrayList<>();
        rotationStates.add(Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2,1)));
        rotationStates.add(Arrays.asList(new Point(0, 1), new Point(0, 2), new Point(1, 0), new Point(1,1)));
        rotationIndex = 0;
    }

    @Override
    public List<Point> getBlockCoordinates() {
        return rotationStates.get(rotationIndex);
    }

    @Override
    public void rotateClockwise() {
        rotationIndex = (rotationIndex + 1) % rotationStates.size();
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }
}
