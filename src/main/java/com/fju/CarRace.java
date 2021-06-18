package com.fju;

import javax.swing.*;
import java.awt.*;


public class CarRace {
    private JFrame frame;
    private JLabel messageLabel;
    private JPanel mainPanel, btnPanel;
    private JButton startBtn, resetBtn;

    public CarRace(){
        frame = new JFrame();
        messageLabel = new JLabel("請按開始按鈕開始比賽");
        mainPanel = new JPanel();
        btnPanel = new JPanel();
        startBtn = new JButton("開始");
        resetBtn = new JButton("重設");

    }
    public static void main(String[] args) {

    }
}
