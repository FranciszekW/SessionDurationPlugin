package franciszekw.sessiondurationplugin;

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
    private final TextPanel textPanel = new TextPanel();
    private final Timer timer;
    private final Instant startTime;

    public SessionDurationWidget() {
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

    private void updateText() {
        Duration duration = Duration.between(startTime, Instant.now());
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        textPanel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    @Override
    public @NotNull String ID() {
        return "SessionDurationWidget";
    }

    @Override
    public @NotNull String getText() {
        return textPanel.getText();
    }

    @Override
    public float getAlignment() {
        // allign to the right
        return 1.0f;
    }

    @Override
    public @Nullable String getTooltipText() {
        return "Current session duration";
    }
}