package com.cloud.streaming.host.server;

public class EventData {
    private int x;
    private int y;
    private int keyCode;
    private double screenWidth;
    private double screenHeight;

    public EventData(int x, int y, double screenHeight, double screenWidth) {
        this.x = x;
        this.y = y;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public EventData(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }
}
