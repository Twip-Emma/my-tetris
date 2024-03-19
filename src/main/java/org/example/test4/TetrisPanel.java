package org.example.test4;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

class TetrisPanel extends JPanel implements ActionListener, KeyListener {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    private final int DELAY = 500; // 方块下落速度，单位：毫秒

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isMoving = false; // 是否正在移动
    private boolean isPaused = false;
    private int currentX = 0;
    private int currentY = 0;
    private Block currentBlock;
    private Set<Point> filledCells; // 用于存储已经在地图上固定的方块
    private final Object lock = new Object(); // 锁对象

    /**
     * 构造方法：初始化背景
     */
    public TetrisPanel() {
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY); // 设置背景为灰色
        setPreferredSize(new Dimension(300, 600));

        timer = new Timer(DELAY, this);
        addKeyListener(this);

        filledCells = new HashSet<>();
    }

    public void startGame() {
        generateNewBlock();
        timer.start();
    }


    private void doGameCycle() {
        synchronized (lock) {
            update();
        }
        repaint();
    }

    /**
     * 游戏主循环
     */
    private void update() {
        if (isFallingFinished) {
            // 1.当前方块已到达边界，需要固定并判断是否需要消除行
            // 固定方块
            fixBlock();
            // 检查并清除已完成的行
            checkCompletedRows();
            // 生成新方块
            generateNewBlock();
            currentX = 0;
            currentY = 0;
            isFallingFinished = false;
        } else {
            // 2.当前方块未到达边界，继续下落
            currentY++;
            if (!isValidMove(currentBlock, currentX, currentY + 1)) {
                // Block reached the bottom or collided with other blocks
                isFallingFinished = true;
            }
        }
    }

    private void generateNewBlock() {
        // 随机生成一个方块
        Random random = new Random();
        int blockType = random.nextInt(4); // 生成 0 到 6 的随机数 (共 7 种方块)
        switch (blockType) {
            case 0 -> currentBlock = new IBlock();
            case 1 -> currentBlock = new OBlock();
            case 2 -> currentBlock = new TBlock();
            case 3 -> currentBlock = new LBlock();

            // 添加其他类型的方块...
            default -> currentBlock = new IBlock(); // 默认情况下，生成 I 型方块
        }
    }


    private void fixBlock() {
        // 固定方块的代码
        for (Point point : currentBlock.getBlockCoordinates()) {
            filledCells.add(new Point(currentX + point.x, currentY + point.y));
        }
    }



    private void checkCompletedRows() {
        // 检查并清除已完成的行
        List<Integer> completedRows = new ArrayList<>();
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean rowCompleted = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (!filledCells.contains(new Point(j, i))) {
                    rowCompleted = false;
                    break;
                }
            }
            if (rowCompleted) {
                completedRows.add(i);
            }
        }

        // Remove completed rows
        if (!completedRows.isEmpty()) {
            for (int row : completedRows) {
                for (int k = 0; k < BOARD_WIDTH; k++) {
                    filledCells.remove(new Point(k, row));
                }
            }
            // Move cells above down
            int numRowsRemoved = completedRows.size();
            for (int row : completedRows) {
                for (int k = row - 1; k >= 0; k--) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        Point p = new Point(j, k);
                        if (filledCells.contains(p)) {
                            filledCells.remove(p);
                            filledCells.add(new Point(j, k + numRowsRemoved));
                        }
                    }
                }
            }
        }
    }


    private boolean isValidMove(Block block, int x, int y) {
        // 检查块是否与任何现有单元格重叠
        for (Point point : block.getBlockCoordinates()) {
            int newX = x + point.x;
            int newY = y + point.y;
            if (newX < 0 || newX >= BOARD_WIDTH || newY >= BOARD_HEIGHT || filledCells.contains(new Point(newX, newY))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw background grid
        g.setColor(Color.WHITE);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                g.drawRect(j * 30, i * 30, 30, 30);
            }
        }

        // Draw filled cells
        for (Point point : filledCells) {
            g.setColor(Color.GREEN); // 已经残留的方块变成绿色
            g.fillRect(point.x * 30, point.y * 30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(point.x * 30, point.y * 30, 30, 30);
        }

        // Draw current block
        if (currentBlock != null) {
            g.setColor(Color.BLACK); // 正在下落的方块组合是黑色
            for (Point point : currentBlock.getBlockCoordinates()) {
                g.fillRect((currentX + point.x) * 30, (currentY + point.y) * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect((currentX + point.x) * 30, (currentY + point.y) * 30, 30, 30);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doGameCycle();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        synchronized (lock) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> {
                    if (!isMoving && isValidMove(currentBlock, currentX - 1, currentY)) {
                        currentX--;
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (!isMoving && isValidMove(currentBlock, currentX + 1, currentY)) {
                        currentX++;
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (!isMoving && isValidMove(currentBlock, currentX, currentY + 1)) {
                        currentY++;
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (!isMoving) {
                        rotateCurrentBlock();
                    }
                }
                default -> {
                }
            }
            repaint();
        }
    }

    private void rotateCurrentBlock() {
        currentBlock.rotateClockwise(); // 顺时针旋转当前方块的状态
        while (!isValidMove(currentBlock, currentX, currentY)) {
            currentBlock.rotateClockwise(); // 如果旋转后的方块位置不合法，则再次旋转回去
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}