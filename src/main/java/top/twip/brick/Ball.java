package top.twip.brick;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Ball {
    private float x, y; // 球的坐标
    private float radius; // 球的半径
    private float speedX, speedY; // 球的速度
    private float acceleration = 0.05f; // 加速度，向下为正方向

    public Ball(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI; // 生成随机角度
        speedX = (float) Math.cos(angle) * 8; // 根据角度计算速度
        speedY = (float) Math.sin(angle) * 8;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.circle(x, y, radius);
    }

    // 更新球的位置
    public void update() {
        // 水平移动
        x += speedX;
        // 垂直移动，并施加加速度
        y += speedY;
        speedY -= acceleration;

        // 碰撞检测
        if (x - radius < 0 || x + radius > 600) {
            speedX = -speedX; // 水平反弹
        }
        if (y - radius < 0 || y + radius > 600) {
            speedY = -speedY; // 垂直反弹
        }
    }

    // 处理与方块的碰撞
    public void collideWithBlock(Block block) {
        if (x + radius >= block.getX() && x - radius <= block.getX() + block.getWidth() &&
                y + radius >= block.getY() && y - radius <= block.getY() + block.getHeight()) {
            // 水平反弹
            if (x < block.getX() || x > block.getX() + block.getWidth()) {
                speedX = -speedX;
            }
            // 垂直反弹
            if (y < block.getY() || y > block.getY() + block.getHeight()) {
                speedY = -speedY;
            }
        }
    }

    // 设置球的速度
    public void setSpeed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    // 获取球的X坐标
    public float getX() {
        return x;
    }

    // 获取球的Y坐标
    public float getY() {
        return y;
    }

    // 获取球的半径
    public float getRadius() {
        return radius;
    }

    // 获取球的速度X分量
    public float getSpeedX() {
        return speedX;
    }

    // 获取球的速度Y分量
    public float getSpeedY() {
        return speedY;
    }
}

