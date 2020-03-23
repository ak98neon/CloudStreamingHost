package com.cloud.streaming.host.gui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    public WindowPanel(MouseEventObserver mouseListener, KeyEventObserver keyListener) {
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addKeyListener(keyListener);
    }

    public void paintImage(Image image) {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
