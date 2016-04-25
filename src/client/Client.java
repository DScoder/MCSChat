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
    protected UI frame;
    private DataInputStream in;
    private DataOutputStream out;
    protected Socket socket;

    public Client() throws IOException {
        addr = InetAddress.getByName(null);
        socket = new Socket(addr, ServerSide.PORT);
        frame = new UI();
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        frame.addTextListener(new TextListener(){
            @Override
            public void textEventOccured(client.TextEvent event) throws IOException {
                String text = event.getText();
                out.writeUTF(text);
                out.flush();
            }
        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    String str = in.readUTF();
                    frame.setText(str);
                    if (str.equals("exit")) {
                        break;
                    }
                } catch (NullPointerException e) {
                    out.writeUTF("exit");
                    out.flush();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            System.out.println("closing...");
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
