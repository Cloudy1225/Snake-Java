package com.cloudy.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args){
        //1.绘制一个静态窗口 JFrame
        JFrame frame = new JFrame("Cloudy: Gluttonous Snake for my brother");
        frame.setBounds(10,10,925,700);//设置界面大小
        frame.setResizable(false);//窗口大小不可以改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件

        //2.面板 JPanel 可以加入到JFrame
        frame.add(new GamePanel());

        frame.setVisible(true);//展现窗口
    }
}
