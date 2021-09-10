package com.snake;

import org.omg.CORBA.DATA_CONVERSION;

import javax.swing.*;
import java.net.URL;
import java.util.Date;

//存放外部数据
public class Data {
    //游戏面板头部 URL、图片
    public static URL headerURL = Date.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //蛇头
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);

    //蛇身
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    //食物
    public static URL foodURL = DATA_CONVERSION.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);

}
