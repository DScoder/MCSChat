package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DScoder on 10.04.2016.
 */
public class ServerSide implements Runnable {
    public static final int PORT = 8189;
    protected static LocalhostFrame frame;
    protected static int clientNumber = 0;
    protected ServerSocket s;

    public ServerSide() throws IOException {
        frame = new LocalhostFrame();
        s = new ServerSocket(PORT);
        frame.setText("Server Started. Waiting connections... ");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = s.accept();
                clientNumber++;
                try {
                    new ServerClientHandler(socket);
                    frame.setText("User" + clientNumber + " connected.");
                } catch (IOException e) {
                    socket.close();
                }
            }
        } catch (IOException e) {

        } finally {
            try {
                s.close();
            } catch (IOException e) {

            }

        }
    }
}
