package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by DScoder on 11.04.2016.
 */
public class ServerClientHandler extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private int number;

    public ServerClientHandler(Socket s) throws IOException {
        socket = s;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        number = ServerSide.clientNumber;
        out.writeUTF("Hello, User #" + ServerSide.clientNumber + ". Tipe 'exit' for close this chat. =)");
        out.flush();
        start();
    }

    public void run() {
        try {
            while (true) {
                try {
                    String str = in.readUTF();
                    if (str.equals("exit")) {
                        break;
                    }
                    out.writeUTF("User" + number + ": " + str);
                    out.flush();
                    ServerSide.frame.setText("User" + number + ": " + str);
                } catch (NullPointerException e) {
                    break;
                }
            }
            ServerSide.frame.setText("User" + number + " exit from chat...");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
                System.out.println("Socket " + number + " was closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}