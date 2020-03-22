package com.cloud.streaming.host.gui;

import com.cloud.streaming.host.server.EventData;

import java.io.OutputStream;

public class MouseEventObserver extends EventObserver implements java.awt.event.MouseListener {

    public MouseEventObserver(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        super.sendEvent(EventType.MOUSE_CLICK);
        int x = e.getX();
        int y = e.getY();
        System.out.println("x:" + x + " y: " + y);
        super.send(new EventData(x, y));
        super.flush();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}