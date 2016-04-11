package client;

import server.ServerSide;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by DScoder on 10.04.2016.
 */
public class Client implements Runnable {
    protected static InetAddress addr;
    protected static UI frame;
    private BufferedReader in;
    private PrintWriter out;
    protected static Socket socket;

    public Client() throws IOException {
        addr = InetAddress.getByName(null);
        socket = new Socket(addr, ServerSide.PORT);
        frame = new UI();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = frame.out;

    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    String str = in.readLine();
                    frame.setText(str);
                    if (str.equals("exit")) {
                        break;
                    }
                } catch (NullPointerException e) {
                    out.println("exit");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            System.out.println("closing...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
