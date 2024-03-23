package top.twip.brick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGame extends ApplicationAdapter {
    private Ball ball;
    private Block block;
    private List<Brick> bricks;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
        ball = new Ball(300, 300, 20);
        block = new Block(275, 50, 150, 20);

        bricks = new ArrayList<Brick>();

        // 生成砖块
        generateBricks();
    }

    @Override
    public void render () {
        // 清除屏幕
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 检查键盘输入
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            block.setPosition(Math.max(0, block.getX() - 5), block.getY());
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            block.setPosition(Math.min(600 - block.getWidth(), block.getX() + 5), block.getY());
        }

        // 更新球的位置
        ball.update();

        // 处理球和方块的碰撞
        ball.collideWithBlock(block);

        // 处理球和砖块的碰撞
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (brick.isVisible() && ball.getX() + ball.getRadius() >= brick.getX() && ball.getX() - ball.getRadius() <= brick.getX() + brick.getWidth() &&
                    ball.getY() + ball.getRadius() >= brick.getY() && ball.getY() - ball.getRadius() <= brick.getY() + brick.getHeight()) {
                // 水平反弹
                if (ball.getX() < brick.getX() || ball.getX() > brick.getX() + brick.getWidth()) {
                    ball.setSpeed(-ball.getSpeedX(), ball.getSpeedY());
                }
                // 垂直反弹
                if (ball.getY() < brick.getY() || ball.getY() > brick.getY() + brick.getHeight()) {
                    ball.setSpeed(ball.getSpeedX(), -ball.getSpeedY());
                }
                brick.setVisible(false); // 砖块消失
            }
        }

        // 渲染球、方块和砖块
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ball.render(shapeRenderer);
        block.render(shapeRenderer);
        for (Brick brick : bricks) {
            brick.render(shapeRenderer);
        }
        shapeRenderer.end();
    }

    // 生成砖块
    private void generateBricks() {
        Random random = new Random();
        int numBricks = 20; // 生成20个砖块
        int brickWidth = 50;
        int brickHeight = 50;
        int numColumns = 12; // 将游戏屏幕分割成12列
        int numRows = 6; // 4行
        int cellWidth = 600 / numColumns;
        int cellHeight = 300 / numRows;

        for (int i = 0; i < numBricks; i++) {
            int col = random.nextInt(numColumns); // 随机选择列
            int row = random.nextInt(numRows); // 随机选择行
            int x = col * cellWidth;
            int y = 300 + row * cellHeight; // 将砖块放在屏幕上半部分的随机行
            Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1); // 随机生成颜色
            Brick brick = new Brick(x, y, brickWidth, brickHeight, color);
            bricks.add(brick);
        }
    }

    @Override
    public void dispose () {
        shapeRenderer.dispose();
    }
}



