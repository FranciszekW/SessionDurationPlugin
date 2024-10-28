package franciszekw.sessiondurationplugin;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.TextPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

public class SessionDurationWidget implements StatusBarWidget, StatusBarWidget.TextPresentation {
    private final static Logger LOG = Logger.getInstance(SessionDurationWidget.class);

    private StatusBar statusBar;
    private final Timer timer;
    private final Instant startTime;
    private String displayText = "00:00:00";

    public SessionDurationWidget() {
        LOG.info("SessionDurationWidget created");
        this.startTime = Instant.now();

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

    private void updateText() {
        try {
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
        return "Current session duration";
    }

    @Override
    public @Nullable WidgetPresentation getPresentation() {
        LOG.info("getPresentation called");
        return this;
    }

    @Override
    public void dispose() {
        if (timer != null) {
            timer.stop();
        }
    }
}