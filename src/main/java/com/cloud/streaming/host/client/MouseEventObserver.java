package com.cloud.streaming.host.client;

import com.cloud.streaming.host.server.EventData;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.OutputStream;

public class MouseEventObserver extends EventObserver implements java.awt.event.MouseListener, MouseMotionListener {

    public MouseEventObserver(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        sendEvent(EventType.MOUSE_CLICK);
        int button = e.getButton();
        send(new EventData(button));
        flush();
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        sendEvent(EventType.MOUSE_MOVE);
        int x = e.getX();
        int y = e.getY();
        System.out.println("x:" + x + " y: " + y);
        Dimension screenSize = getScreenSize();
        send(new EventData(x, y, screenSize.getHeight(), screenSize.getWidth()));
        flush();
    }
}
