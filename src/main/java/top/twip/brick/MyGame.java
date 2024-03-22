package top.twip.brick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class MyGame extends ApplicationAdapter {
    private Ball ball;
    private Block block;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
        ball = new Ball(300, 300, 20);
        Random random = new Random();
        block = new Block(random.nextInt(400), random.nextInt(400), 100, 50);
    }

    @Override
    public void render () {
        // 清除屏幕
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 检查键盘输入
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            block.setPosition(block.getX() - 5, block.getY());
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            block.setPosition(block.getX() + 5, block.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            block.setPosition(block.getX(), block.getY() + 5);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            block.setPosition(block.getX(), block.getY() - 5);
        }

        // 更新球的位置
        ball.update();

        // 处理球和方块的碰撞
        ball.collideWithBlock(block);

        // 渲染球和方块
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ball.render(shapeRenderer);
        block.render(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void dispose () {
        shapeRenderer.dispose();
    }
}

