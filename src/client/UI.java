package client;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by DScoder on 10.04.2016.
 */
public class UI extends JFrame {

    protected JTextArea textArea;
    public EventListenerList listenerList = new EventListenerList();


    public UI() throws IOException {
        setTitle("Chat");
        setLocation(600, 50);
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        JScrollPane jsp = new JScrollPane(textArea);
        jsp.setAutoscrolls(true);
        textArea.setAutoscrolls(true);
        add(jsp, BorderLayout.CENTER);

        final JPanel messagePanel = new JPanel(new BorderLayout());
        final JTextField userText = new JTextField();
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userText.getText();
                userText.setText("");
                userText.setCaretPosition(userText.getDocument().getLength());
                fireTextEvent(new TextEvent(this,text));
            }
        });
        messagePanel.add(userText);
        JButton sendButton = new JButton("SEND");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userText.getText();
                userText.setText("");
                userText.setCaretPosition(userText.getDocument().getLength());
                fireTextEvent(new TextEvent(this,text));
            }
        });
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);


        setVisible(true);
    }

    public void fireTextEvent(TextEvent event){
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TextListener.class){
                try {
                    ((TextListener)listeners[i+1]).textEventOccured(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addTextListener(TextListener listener){
        listenerList.add(TextListener.class, listener);
    }

    public void removeTextListener(TextListener listener){
        listenerList.remove(TextListener.class, listener);
    }

    public void setText(String message) {
        textArea.append(message + "\n");
    }
}
