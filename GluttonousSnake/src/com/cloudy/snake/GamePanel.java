package com.cloudy.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length; //蛇的长度
    int[] snakeX = new int[600];//蛇的坐标X
    int[] snakeY = new int[500];//蛇的坐标Y
    String direction;//R：右，U：上，D：下，L：左
    boolean isStart = false;//游戏是否开始
    Timer timer = new Timer(100, this);//定时器，一秒十帧

    //定义一个食物
    int foodX, foodY;
    Random random = new Random();

    //是否失败
    boolean isFail;

    //积分系统
    int score;

    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//时间开始流动
    }

    //初始化
    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;//头部坐标
        snakeX[1] = 75;
        snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;//第二个身体坐标
        direction = "R";
        //isStart = false;//加上的话，失败后须按两次空格以重新开始
        foodX = 25 + 25 * random.nextInt(33);
        foodY = 75 + 25 * random.nextInt(19);
        isFail = false;
        score = 0;
    }

    //画板：画界面，画蛇
    //Graphics ：画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏

        this.setBackground(Color.WHITE);//设置背景颜色
        Data.header.paintIcon(this, g, 25, 10);//绘制header
        g.fillRect(25, 75, 850, 550);//绘制游戏区域

        //画一条静态的蛇
        switch (direction) {
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }//蛇的身体长度由length来控制

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + length, 775, 30);
        g.drawString("分数：" + score, 775, 50);

        //画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        //游戏提示：是否开始
        if (!isStart) {
            //画一个String
            g.setColor(Color.WHITE);//设置画笔的颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));//设置字体
            g.drawString("按下空格开始或继续游戏", 225, 350);
        }

        //失败提醒
        if (isFail) {
            //画一个String
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败，按下空格重新开始游戏", 225, 350);
        }

    }

    //接收键盘的输入；监听
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下哪个键
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {//如果按下的是空格键
            if (isFail) {//失败，游戏再来一遍
                //isFail = false;
                init();//重新初始化游戏
            } else {
                isStart = !isStart;//实现暂停、继续、开始等功能
            }
            repaint();//重新绘制界面，即刷新界面
        }

        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT) {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = "D";
        }
    }


    //定时器，监听时间，帧：执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且没有结束
        if (isStart && !isFail) {
            //右移
            for (int i = length - 1; i > 0; i--) {//身体移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过控制方向让头部移动
            switch (direction) {
                case "R":
                    snakeX[0] = snakeX[0] + 25;//头部移动
                    if (snakeX[0] > 850) {
                        snakeX[0] = 25;
                    }//边界判断
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 25;//头部移动
                    if (snakeX[0] < 25) {
                        snakeX[0] = 850;
                    }//边界判断
                    break;
                case "U":
                    snakeY[0] = snakeY[0] - 25;//头部移动
                    if (snakeY[0] < 75) {
                        snakeY[0] = 600;
                    }//边界判断
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 25;//头部移动
                    if (snakeY[0] > 600) {
                        snakeY[0] = 75;
                    }//边界判断
                    break;
            }

            //判断是否吃到食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;//长度+1
                foodX = 25 + 25 * random.nextInt(33);
                foodY = 75 + 25 * random.nextInt(19);//重新生成食物
                score += 10;
            }

            //判断是否失败
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    break;
                }
            }

            //刷新界面
            repaint();
        }
        timer.start();//时间流动
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}