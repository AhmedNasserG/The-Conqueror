package listeners;

import java.io.IOException;

public interface NewGameListener {
    public void onPlayClicked(String selectedLevel) throws IOException;
}
