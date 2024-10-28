package franciszekw.sessiondurationplugin;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SessionDurationWidgetFactory implements StatusBarWidgetFactory {
    private static final Logger LOG = Logger.getInstance(SessionDurationWidgetFactory.class);

    @Override
    public @NotNull @NonNls String getId() {
        LOG.info("getId called");
        return "SessionDurationWidgetFactory";
    }

    @Override
    public @NotNull @NlsContexts.Label String getDisplayName() {
        LOG.info("getDisplayName called");
        return "Session Duration";
    }

    public boolean isAvailable(@NotNull StatusBar statusBar) {
        LOG.info("isAvailable called");
        return true;
    }

    public @NotNull StatusBarWidget createWidget(@NotNull StatusBar statusBar) {
        LOG.info("createWidget called");
        return new SessionDurationWidget();
    }

    public void disposeWidget(@NotNull StatusBarWidget widget) {
        LOG.info("disposeWidget called");
        // do nothing
    }
}
