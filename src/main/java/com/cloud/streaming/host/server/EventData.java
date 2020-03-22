package com.cloud.streaming.host.server;

public class EventData {
    private int x;
    private int y;
    private int keyCode;

    public EventData(int x, int y) {
        this.x = x;
        this.y = y;
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
}
