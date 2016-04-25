package client;

import java.io.IOException;
import java.util.EventListener;

/**
 * Created by DScoder on 17.04.2016.
 */
public interface TextListener extends EventListener {
    public void textEventOccured(TextEvent event) throws IOException;
}
