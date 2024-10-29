package franciszekw.sessiondurationplugin;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A project activity that resets the session timer when the project is opened.
 */
public class TimeResetter implements ProjectActivity {
    @Override
    public @Nullable Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        SessionDurationState state = project.getService(SessionDurationState.class);
        // Reset the timer to 0, so that it will be launched from the beginning next time
        state.reset();

        return null;
    }
}