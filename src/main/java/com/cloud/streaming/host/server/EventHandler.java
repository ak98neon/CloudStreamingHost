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

    public EventHandler(Socket socket) throws AWTException, IOException {
        inputStream = socket.getInputStream();
    }

    public void handleMousePress(EventData data) {
        robot.mouseMove(data.getX(), data.getY());
    }

    public void handleMouseMove(EventData data) {
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

                String action;
                EventData eventData;
                switch (EventType.valueOf(data)) {
                    case MOUSE_CLICK:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleMousePress(eventData);
                        break;
                    case KEY_TYPED:
                        action = scanner.nextLine();
                        eventData = gson.fromJson(action, EventData.class);
                        handleKeyPress(eventData);
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
