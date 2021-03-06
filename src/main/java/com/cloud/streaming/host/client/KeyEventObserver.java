package com.cloud.streaming.host.client;

import com.cloud.streaming.host.server.EventData;

import java.awt.event.KeyListener;
import java.io.OutputStream;

public class KeyEventObserver extends EventObserver implements KeyListener {

    public KeyEventObserver(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        sendEvent(EventType.KEY_PRESSED);
        int keyCode = e.getKeyCode();
        System.out.println("key: " + keyCode);
        send(new EventData(keyCode));
        flush();
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        sendEvent(EventType.KEY_RELEASE);
        int keyCode = e.getKeyCode();
        System.out.println("key: " + keyCode);
        send(new EventData(keyCode));
        flush();
    }
}
