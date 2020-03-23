package com.cloud.streaming.host.client;

public enum EventType {
    MOUSE_CLICK(1),
    KEY_TYPED(2),
    MOUSE_MOVE(3),
    ;

    private int code;

    EventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
