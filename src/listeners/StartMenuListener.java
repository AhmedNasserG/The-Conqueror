package listeners;

import java.io.IOException;

public interface StartMenuListener {
    void onNewGameClicked() throws IOException;
    void onLeadboardClicked() throws IOException;
}
