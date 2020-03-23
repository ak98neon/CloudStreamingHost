package com.cloud.streaming.host.server;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

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

            EventHandler eventHandler = new EventHandler(socket);
            eventHandler.start();

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Rectangle shotArea = new Rectangle(toolkit.getScreenSize());

            while (true) {
                BufferedImage screenCapture = robot.createScreenCapture(shotArea);

                ImageIO.write(screenCapture, "png", tmp);
                System.out.println("Image size: " + tmp.size());
//                ImageIO.write(screenCapture, "jpeg", outputStream);
                writeJPG(screenCapture, outputStream, 0.3F);
                tmp.reset();
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

    public void writeJPG(
            BufferedImage bufferedImage,
            OutputStream outputStream,
            float quality) throws IOException {
        Iterator<ImageWriter> iterator =
                ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter imageWriter = iterator.next();

        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(quality);

        ImageOutputStream imageOutputStream =
                new MemoryCacheImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        IIOImage iioimage = new IIOImage(bufferedImage, null, null);
        imageWriter.write(null, iioimage, imageWriteParam);
        imageOutputStream.flush();
    }
}
