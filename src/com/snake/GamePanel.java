package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


/**
 * 游戏界面相关
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int lenth;//蛇的长度
    int[] snakeX = new int[600];//蛇的X坐标
    int[] snakeY = new int[500];//蛇的Y坐标
    String fx;//方向 R右 L左 U上 D下

    boolean isStart;//游戏是否开始
    boolean isFail;//死亡判断

    //每隔100毫秒执行一次接口ActionListener的方法actionPerformed
    Timer timer = new Timer(100, this);//定时器

    //定义食物
    int foodx;
    int foody;
    Random random = new Random();

    //积分
    int score;

    public GamePanel() {
        init();
        this.setFocusable(true);//true 获取焦点
        this.addKeyListener(this);//获取键盘监听事件
        timer.start();//让时间动起来
    }

    //初始化
    public void init() {
        lenth = 3;
        //蛇头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;

        //蛇第一个身体坐标
        snakeX[1] = 75;
        snakeY[1] = 100;

        //蛇第二个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;

        //朝向
        fx = "R";

        //随机坐标食物
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);

        score = 0;
    }


    /**
     * 画板画制
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

        //绘制头部的广告栏
        Data.header.paintIcon(this, g, 25, 11);

        //绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        //画一条静态蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + lenth, 750, 35);
        g.drawString("分数：" + score, 750, 50);

        //画食物
        Data.food.paintIcon(this, g, foodx, foody);

        if (!isStart) {
            //画提示语
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        if (isFail) {
            //失败提醒
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏失败，按下空格重新开始", 200, 300);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //获取键盘按键事件
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {//空格键 开始停止游戏
            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint();//刷新界面
        }

        //键盘控制
        if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //游戏处于开始状态
        if (isStart && !isFail) {
            for (int i = lenth; i > 0; i--) {
                //除了蛇头，身体向前移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            //通过控制方向让蛇头移动
            switch (fx) {
                case "R":
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > 850) snakeX[0] = 25;
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) snakeX[0] = 850;
                    break;
                case "U":
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) snakeY[0] = 650;
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > 650) snakeY[0] = 75;
                    break;
            }

            //如果蛇头和食物重合，坐标重合，蛇身++
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                //身体长度+1
                lenth++;
                score += 10;//分数增加

                //重新生产食物，随机坐标食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }

            //结束判断
            for (int i = 1; i < lenth; i++) {
                if (snakeY[0] == snakeY[i] && snakeX[0] == snakeX[i]) {
                    isFail = true;
                }
            }

            repaint();//刷新界面
        }
        timer.start();//让时间启起来
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
