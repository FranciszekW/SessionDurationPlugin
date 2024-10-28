package franciszekw.sessiondurationplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

@State(name = "SessionDurationState", storages = @Storage("sessionDuration.xml"))
public class SessionDurationState implements PersistentStateComponent<SessionDurationState> {
    public long startTime; // Store as epoch milliseconds

    @Override
    public SessionDurationState getState() {
        return this;
    }

    @Override
    public void loadState(SessionDurationState state) {
        this.startTime = state.startTime;
    }

    public void reset() {
        this.startTime = 0;
    }
}
