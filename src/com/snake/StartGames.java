package com.snake;

import javax.swing.*;

/**
 * Demoe 贪吃蛇
 *
 * @author justguang
 * @version 1.8
 * @date 20210910
 */
public class StartGames {
    public static void main(String[] args) {
        //绘制静态窗口 JFrame
        JFrame frame = new JFrame("Java-贪吃蛇");

        //设置界面大小
        frame.setBounds(10, 10, 900, 720);

        //设置窗口大小不可改变
        frame.setResizable(false);

        //设置窗口关闭事件
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口可见
        frame.setVisible(true);

        //添加面板
        frame.add(new GamePanel());

    }
}
