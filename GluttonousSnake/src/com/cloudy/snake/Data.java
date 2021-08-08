package com.cloudy.snake;

import javax.swing.*;
import java.net.URL;

//存放素材数据
public class Data {

    //header URL：定位图片的地址 ImageIcon：图片
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //head
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downURL);
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);

    //body
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    //food
    public static URL foodURL = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
}

