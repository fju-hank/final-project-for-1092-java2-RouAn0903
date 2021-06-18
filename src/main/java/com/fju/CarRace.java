package com.fju;

import javax.swing.*;
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
    public static void main(String[] args) {

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
}
