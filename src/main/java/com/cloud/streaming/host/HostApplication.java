package com.cloud.streaming.host;

import com.cloud.streaming.host.server.MachineClient;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
        String externalIpAddress = getExternalIpAddress();
        System.out.println("Server started, ip: " + externalIpAddress);
        while (true) {
            Socket accept = socket.accept();
            System.out.println("Connect a new client");
            new MachineClient(accept).start();
        }
    }

    private static String getExternalIpAddress() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());

        final String amazonCheckerIp = "http://checkip.amazonaws.com/";
        ResponseEntity<String> exchange =
                restTemplate.exchange(amazonCheckerIp, HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }
}
