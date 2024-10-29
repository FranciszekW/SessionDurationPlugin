package franciszekw.sessiondurationplugin;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

/**
 * A widget that displays the duration of the current project session in the status bar.
 */
public class SessionDurationWidget implements StatusBarWidget, StatusBarWidget.TextPresentation {
    private final static Logger LOG = Logger.getInstance(SessionDurationWidget.class);

    private StatusBar statusBar;
    private final Timer timer;
    private Instant startTime;
    private String displayText = "00:00:00";
    SessionDurationState state;

    public SessionDurationWidget(@NotNull Project project) {
        LOG.info("SessionDurationWidget created");

        // load the previous state
        state = project.getService(SessionDurationState.class);
        if (state.startTime == 0) {
            state.startTime = Instant.now().toEpochMilli();
        }
        this.startTime = Instant.ofEpochMilli(state.startTime);

        // refresh the text every second
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // update the text
                updateText();
            }
        });
        this.timer.start();
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        this.statusBar = statusBar;
        LOG.info("SessionDurationWidget installed");
    }

    private boolean was_updated = false;

    /**
     * Updates the display text with the current session duration.
     */
    private void updateText() {
        // It's the only way that I managed to fix the bug with the widget not updating
        // when closing the IDE and opening it again.
        // We simply check if the state was reset and if the widget was not updated yet.
        try {
            if (!was_updated && state.startTime == 0) {
                was_updated = true;
                startTime = Instant.now();
                state.startTime = startTime.toEpochMilli();
            }
            Duration duration = Duration.between(startTime, Instant.now());
            long seconds = duration.getSeconds();
            long minutes = seconds / 60;
            long hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            String text = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            LOG.info("Updating text to " + text);
            this.displayText = text;

            if (statusBar != null) {
                statusBar.updateWidget(ID());
            }
        } catch (Exception e) {
            LOG.error("Error updating text", e);
        }
    }

    @Override
    public @NotNull String ID() {
        LOG.info("ID called");
        return "SessionDurationWidget";
    }

    @Override
    public @NotNull String getText() {
        LOG.info("getText called");
        return displayText;
    }

    @Override
    public float getAlignment() {
        LOG.info("getAlignment called");
        // align to the right
        return 1.0f;
    }

    @Override
    public @Nullable String getTooltipText() {
        LOG.info("getTooltipText called");
        return "Current session duration: " + displayText;
    }

    @Override
    public @Nullable WidgetPresentation getPresentation() {
        LOG.info("getPresentation called");
        return this;
    }

    @Override
    public void dispose() {
        LOG.info("SessionDurationWidget disposed");
        if (timer != null) {
            timer.stop();
        }
    }
}