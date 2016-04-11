package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static client.Client.socket;

/**
 * Created by DScoder on 10.04.2016.
 */
public class UI extends JFrame {

    protected JTextArea textArea;
    protected PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);


    public UI() throws IOException {
        setTitle("Chat");
        setLocation(600, 50);
        setSize(400, 300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        textArea = new JTextArea();
        add(textArea);

        final JPanel messagePanel = new JPanel(new BorderLayout());
        final JTextField userText = new JTextField();
        messagePanel.add(userText);
        JButton sendButton = new JButton("SEND");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println(userText.getText());
                userText.setText("");

            }
        });
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);


        setVisible(true);
    }

    public void setText(String message) {
        textArea.append(message + "\n");
    }
}
