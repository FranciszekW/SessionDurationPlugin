package franciszekw.sessiondurationplugin;

import com.intellij.openapi.components.Service;

/**
 * This class represents the state of the session duration.
 */
@Service
public final class SessionDurationState {
    // The start time of the session, stored as epoch milliseconds
    public long startTime;
}
