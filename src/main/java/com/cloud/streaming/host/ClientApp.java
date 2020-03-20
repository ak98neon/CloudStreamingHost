package com.cloud.streaming.host;

import com.cloud.streaming.host.gui.WindowPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientApp extends Thread {
    private Socket socket;

    public ClientApp() throws IOException {
        this.socket = new Socket("77.122.17.64", NetworkConstants.PORT);
    }

    @Override
    public void run() {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            WindowPanel windowPanel = new WindowPanel();
            JFrame window = new JFrame("Streaming screen");
            window.add(windowPanel);
            window.setSize(screenSize);
            window.setResizable(true);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setVisible(true);

            while (true) {
                byte[] bytes = new byte[1024 * 1024];
                int count = 0;
                do {
                    count += socket.getInputStream().read(bytes, count, bytes.length - count);
                } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
                System.out.println("Receive size: " + count);

                Image image = ImageIO.read(new ByteArrayInputStream(bytes));
                image = image.getScaledInstance(windowPanel.getWidth(), windowPanel.getHeight(), Image.SCALE_FAST);

                Graphics graphics = windowPanel.getGraphics();
                graphics.drawImage(image, 0, 0, windowPanel.getWidth(), windowPanel.getHeight(), windowPanel);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            closeSocket();
        } finally {
            closeSocket();
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
