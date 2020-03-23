package com.cloud.streaming.host.server;

import com.cloud.streaming.host.client.EventType;
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
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public EventHandler(Socket socket) throws AWTException, IOException {
        inputStream = socket.getInputStream();
    }

    public void handleMousePress(EventData data) {
        robot.mousePress(data.getKeyCode());
    }

    public void handleMouseRelease(EventData data) {
        robot.mouseRelease(data.getKeyCode());
    }

    public void handleMouseMove(EventData data) {
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((data.getX() * screenSize.getWidth()) / data.getScreenWidth());
        int y = (int) ((data.getY() * screenSize.getHeight()) / data.getScreenHeight());
        robot.mouseMove(x, y);
    }

    public void handleKeyPress(EventData data) {
        robot.keyPress(data.getKeyCode());
    }

    public void handleKeyRelease(EventData data) {
        robot.keyRelease(data.getKeyCode());
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(inputStream);
        while (true) {
            if (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println("data: " + data);

                String action;
                EventData eventData;
                switch (EventType.valueOf(data)) {
                    case MOUSE_PRESS:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleMousePress(eventData);
                        break;
                    case MOUSE_RELEASE:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleMouseRelease(eventData);
                        break;
                    case KEY_PRESSED:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleKeyPress(eventData);
                        break;
                    case KEY_RELEASE:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleKeyRelease(eventData);
                        break;
                    case MOUSE_MOVE:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleMouseMove(eventData);
                        break;
                }
            }
        }
    }
}
