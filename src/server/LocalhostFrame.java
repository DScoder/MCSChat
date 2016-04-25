package server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DScoder on 10.04.2016.
 */
public class LocalhostFrame extends JFrame {
    private JTextArea textArea;

    public LocalhostFrame(){
        setTitle("Localhost Chat");
        setLocation(250, 50);
        setSize(300, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        add(textArea);

        setVisible(true);
    }

    public void setText(String message) {
        textArea.append(message + "\n");
    }
}
