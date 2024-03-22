package top.twip.brick;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {
    public static void main(String[] args) {
        // 配置游戏窗口
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "爷的打砖块"; // 设置窗口标题
        config.width = 600; // 设置窗口宽度
        config.height = 600; // 设置窗口高度

        // 创建游戏实例并启动
        new LwjglApplication(new MyGame(), config);
    }
}

