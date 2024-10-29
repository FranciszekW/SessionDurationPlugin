package franciszekw.sessiondurationplugin;

import com.intellij.execution.startup.BeforeRunStartupTasks;
import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An application activity that resets the session timer when the IDE is opened.
 */
public class TimeResetter implements AppLifecycleListener {
    private final static Logger LOG = Logger.getInstance(SessionDurationWidget.class);

    @Override
    public void appStarted() {
        LOG.info("App started, resetting session timer");
        ApplicationManager.getApplication().getService(SessionDurationState.class).reset();
    }
}