package franciszekw.sessiondurationplugin;

import com.intellij.execution.startup.BeforeRunStartupTasks;
import com.intellij.ide.ApplicationInitializedListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationStarter;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.startup.StartupActivity;
import io.ktor.http.ContentType;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An application activity that resets the session timer when the IDE is opened.
 */
public class TimeResetter implements BeforeRunStartupTasks {
    private final static Logger LOG = Logger.getInstance(SessionDurationWidget.class);

    @Override
    public @Nullable Object beforeRun(@NotNull Continuation<? super Unit> continuation) {
        LOG.info("Resetting session timer");

        // Reset the session timer
        ApplicationManager.getApplication().getService(SessionDurationState.class).reset();
        return null;
    }
}