package com.cloud.streaming.host.gui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {

    public void paintImage(Image image) {
        this.getGraphics().drawImage(image, 0, 0, this);
    }
}
