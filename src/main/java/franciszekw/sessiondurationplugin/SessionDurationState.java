package franciszekw.sessiondurationplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;

/**
 * This class represents the state of the session duration plugin.
 * It implements the PersistentStateComponent interface to allow
 * the state to be persisted across hiding and showing the widget.
 */
@State(name = "SessionDurationState", storages = @Storage("sessionDuration.xml"))
public class SessionDurationState implements PersistentStateComponent<SessionDurationState> {
    private final static Logger LOG = Logger.getInstance(SessionDurationWidget.class);

    // The start time of the session, stored as epoch milliseconds
    public long startTime;

    @Override
    public SessionDurationState getState() {
        return this;
    }

    @Override
    public void loadState(SessionDurationState state) {
        this.startTime = state.startTime;
    }

    public void reset() {
        LOG.info("SessionDurationState reset called");
        this.startTime = 0;
    }
}
