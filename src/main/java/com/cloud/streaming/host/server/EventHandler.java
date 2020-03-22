package com.cloud.streaming.host.server;

import com.google.gson.Gson;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class EventHandler extends Thread {
    private Robot robot = new Robot();
    private InputStream inputStream;
    private Gson gson = new Gson();

    public EventHandler(Socket socket) throws AWTException, IOException {
        inputStream = socket.getInputStream();
    }

    public void handleMousePress(EventData data) {
        robot.mouseMove(data.getX(), data.getY());
    }

    public void handleKeyPress(EventData data) {
        robot.keyPress(data.getKeyCode());
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(inputStream);
        while (true) {
            if (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println("data: " + data);
            }
        }
    }
}
