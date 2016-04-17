package client;

import java.util.EventObject;

/**
 * Created by DScoder on 17.04.2016.
 */
public class TextEvent extends EventObject {

    private String text;

    public TextEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
