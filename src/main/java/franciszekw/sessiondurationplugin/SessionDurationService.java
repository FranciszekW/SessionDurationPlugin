package franciszekw.sessiondurationplugin;

import com.intellij.openapi.components.Service;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;

@Service
public final class SessionDurationService {
    private Instant startTime;

    public SessionDurationService() {
        startTime = Instant.now(); // Initialize the start time when the service is created
    }

    public long getDuration() {
        return Duration.between(startTime, Instant.now()).toMillis(); // Get the duration in milliseconds
    }

    public void reset() {
        startTime = Instant.now(); // Reset the start time
    }
}
