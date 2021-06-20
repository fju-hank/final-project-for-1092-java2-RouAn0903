package com.fju;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CarRace {
    private JFrame frame;
    private JLabel messageLabel;
    private JPanel mainPanel, btnPanel;
    private JButton startBtn, resetBtn;
    private Car[] cars;
    private boolean gameCompleted;

    public CarRace(){
        frame = new JFrame();
        messageLabel = new JLabel("請按開始按鈕開始比賽");
        mainPanel = new JPanel();
        btnPanel = new JPanel();
        startBtn = new JButton("開始");
        resetBtn = new JButton("重設");
        cars = new Car[]{new Car(1), new Car(2),  new Car(3), new Car(4), new Car(5)};
    }
    public void launchFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(messageLabel , BorderLayout.NORTH);
        startBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("比賽開始.......");
                //建立一個五輛車的執行緒陣列,逐一啟動
                Thread[] ts = new Thread[cars.length];
                for(int i=0; i<ts.length; i++){
                    ts[i] = new Thread(cars[i]);
                    ts[i].start();
                }
            }
        });

        resetBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //畫面重設, 比賽回復開始前狀態
                //gameCompleted設為false
                gameCompleted = false;
                //mainPanel逐一移除Cars Label
                for(int i=0; i<cars.length; i++){
                    mainPanel.remove(cars[i]);
                }
                //重新建構Cars陣列,並設定Cars Label顯示位置
                cars = new Car[]{ new Car(1), new Car(2), new Car(3), new Car(4), new Car(5)};
                for(int i=0;  i<cars.length; i++){
                    cars[i].setLocation(20, i*100);
                    mainPanel.add(cars[i]);
                }
                //重設顯示訊息
                messageLabel.setText("請按開始按鈕開始比賽!");
                //重新繪製Frame
                frame.repaint();
            }

        });

        btnPanel.add(startBtn);
        btnPanel.add(resetBtn);
        frame.add(btnPanel, BorderLayout.SOUTH);
        mainPanel.setLayout(null);
        for(int i=0; i<cars.length; i++){
            cars[i].setLocation(20,i*100);
            mainPanel.add(cars[i]);
        }
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setSize(900,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CarRace gui = new CarRace();
        gui.launchFrame();
    }

    class Car extends JLabel implements Runnable {
        private int track;

        Car(int no) {
            this.track = no;
            ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/car3.png")));
            this.setSize(ii.getIconWidth(), ii.getIconHeight());
            this.setIcon(ii);
        }

        @Override
        public void run() {
            //多執行緒執行內容....
            while (!gameCompleted) {
                this.setLocation(this.getLocation().x + (int) (Math.random() * 50), this.getLocation().y);
                if (this.getLocation().x > 750) {
                   gameCompleted = true;
                   messageLabel.setText("比賽結束!第"+track+"台車獲勝!");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}//
