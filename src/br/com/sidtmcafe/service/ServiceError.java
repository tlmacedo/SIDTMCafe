package br.com.sidtmcafe.service;

import javafx.stage.Stage;

public class ServiceError implements Runnable {

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        try {
            int originalX = (int) stage.getX();
            long sleepTime = 40;

            for (int i = 0; i <= 8; i++) {
                Thread.sleep(sleepTime);
                if (i % 2 == 0) {
                    stage.setX(originalX + 5);
                } else {
                    stage.setX(originalX - 5);
                }
            }
            Thread.sleep(sleepTime);

            stage.setX(originalX);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
