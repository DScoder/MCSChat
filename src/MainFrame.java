import client.Client;
import server.ServerSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by DScoder on 11.04.2016.
 */
public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("DS Chat");
        setLocation(50, 50);
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        JButton localhostButton = new JButton("LOCALHOST");
        localhostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Thread(new ServerSide()).start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(localhostButton);

        JButton clientButton = new JButton("Client");
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Thread(new Client()).start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(clientButton);

        setVisible(true);
    }

    public static void main(String[] args) {
         new MainFrame();
    }
}
