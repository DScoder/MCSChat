package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by DScoder on 11.04.2016.
 */
public class ServerClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int number;

    public ServerClientHandler(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        number = ServerSide.clientNumber;
        out.println("Hello, User #" + ServerSide.clientNumber + ". Tipe 'exit' for close this chat. =)");
        start();
    }

    public void run() {
        try {
            while (true) {
                try {
                    String str = in.readLine();
                    if (str.equals("exit")) {
                        break;
                    }
                    out.println("User" + number + ": " + str);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}