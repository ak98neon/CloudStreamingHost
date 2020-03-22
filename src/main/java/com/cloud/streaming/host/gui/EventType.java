package com.cloud.streaming.host.gui;

public enum EventType {
    MOUSE_CLICK(1),
    KEY_PRESS(2),
    ;

    private int code;

    EventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
