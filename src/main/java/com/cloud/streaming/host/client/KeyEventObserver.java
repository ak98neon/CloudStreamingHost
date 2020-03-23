package com.cloud.streaming.host.client;

import com.cloud.streaming.host.server.EventData;

import java.io.OutputStream;

public class KeyEventObserver extends EventObserver implements java.awt.event.KeyListener {

    public KeyEventObserver(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        super.sendEvent(EventType.KEY_TYPED);
        int keyCode = e.getKeyCode();
        System.out.println("key: " + keyCode);
        super.send(new EventData(keyCode));
        super.flush();
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

    }
}
