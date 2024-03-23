package top.twip.brick;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick {
    private float x, y; // 砖块的左上角坐标
    private float width, height; // 砖块的宽度和高度
    private Color color; // 砖块的颜色
    private boolean visible; // 是否可见

    public Brick(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.visible = true;
    }

    public void render(ShapeRenderer renderer) {
        if (visible) {
            renderer.setColor(color);
            renderer.rect(x, y, width, height);
        }
    }

    // 获取砖块的左上角X坐标
    public float getX() {
        return x;
    }

    // 获取砖块的左上角Y坐标
    public float getY() {
        return y;
    }

    // 获取砖块的宽度
    public float getWidth() {
        return width;
    }

    // 获取砖块的高度
    public float getHeight() {
        return height;
    }

    // 判断砖块是否可见
    public boolean isVisible() {
        return visible;
    }

    // 设置砖块可见性
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

