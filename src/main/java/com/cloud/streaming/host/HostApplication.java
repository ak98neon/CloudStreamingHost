package com.cloud.streaming.host;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class HostApplication {

    public static void main(String[] args) throws IOException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(HostApplication.class);
        builder.headless(false);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);

        ServerSocket socket = new ServerSocket(NetworkConstants.PORT);
        System.out.println("Server started");
        while (true) {
            Socket accept = socket.accept();
            System.out.println("Connect a new client");
            new MachineClient(accept).start();
        }
    }
}
