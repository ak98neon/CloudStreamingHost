package com.cloud.streaming.host;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MachineClient extends Thread {
    private Socket socket;
    private OutputStream outputStream;

    public MachineClient(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
        try (ByteArrayOutputStream tmp = new ByteArrayOutputStream()) {
            Robot robot = new Robot();

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Rectangle shotArea = new Rectangle(toolkit.getScreenSize());

            while (true) {
                BufferedImage screenCapture = robot.createScreenCapture(shotArea);

                ImageIO.write(screenCapture, "png", tmp);
                tmp.close();
                System.out.println("Image size: " + tmp.size());
                ImageIO.write(screenCapture, "jpeg", outputStream);
            }
        } catch (Exception e) {
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
