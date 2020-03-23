package com.cloud.streaming.host.client;

public enum EventType {
    MOUSE_CLICK(1),
    MOUSE_PRESS(2),
    MOUSE_RELEASE(3),
    KEY_RELEASE(4),
    KEY_PRESSED(5),
    MOUSE_MOVE(6),
    ;

    private int code;

    EventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
