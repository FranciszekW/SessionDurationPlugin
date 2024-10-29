package franciszekw.sessiondurationplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;

/**
 * This class represents the state of the session duration.
 */
@Service
public final class SessionDurationState {
    // The start time of the session, stored as epoch milliseconds
    public long startTime;
}
