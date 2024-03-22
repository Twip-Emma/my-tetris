package top.twip.brick;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    private float x, y; // 方块的坐标
    private float width, height; // 方块的宽度和高度

    public Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.rect(x, y, width, height);
    }

    // 设置方块的位置
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // 获取方块的X坐标
    public float getX() {
        return x;
    }

    // 获取方块的Y坐标
    public float getY() {
        return y;
    }

    // 获取方块的宽度
    public float getWidth() {
        return width;
    }

    // 获取方块的高度
    public float getHeight() {
        return height;
    }
}

