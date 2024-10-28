package franciszekw.sessiondurationplugin;

import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SessionDurationWidgetFactory implements StatusBarWidgetFactory {
    @Override
    public @NotNull @NonNls String getId() {
        return "SessionDurationWidgetFactory";
    }

    @Override
    public @NotNull @NlsContexts.Label String getDisplayName() {
        return "Session Duration";
    }

    public boolean isAvailable(@NotNull StatusBar statusBar) {
        return true;
    }

    public @NotNull StatusBarWidget createWidget(@NotNull StatusBar statusBar) {
        return new SessionDurationWidget();
    }

    public void disposeWidget(@NotNull StatusBarWidget widget) {
        // do nothing
    }
}
