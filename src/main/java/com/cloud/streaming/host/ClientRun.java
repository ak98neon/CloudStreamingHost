package com.cloud.streaming.host;

import com.cloud.streaming.host.client.ClientApp;

import java.io.IOException;

public class ClientRun {
    public static void main(String[] args) throws IOException {
        new ClientApp().start();
    }
}
