package com.cloud.streaming.host.client;

import com.cloud.streaming.host.server.EventData;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

public abstract class EventObserver {
    private PrintWriter printWriter;
    private Gson gson = new Gson();

    public EventObserver(OutputStream outputStream) {
        Objects.requireNonNull(outputStream);
        this.printWriter = new PrintWriter(outputStream);
    }

    public void sendEvent(EventType eventType) {
        printWriter.println(eventType);
    }

    public void send(EventData object) {
        String json = gson.toJson(object);
        printWriter.println(json);
    }

    public void flush() {
        printWriter.flush();
    }
}
