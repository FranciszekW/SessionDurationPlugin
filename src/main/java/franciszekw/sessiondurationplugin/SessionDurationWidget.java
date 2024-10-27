package franciszekw.sessiondurationplugin;

import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.TextPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class SessionDurationWidget implements StatusBarWidget, StatusBarWidget.TextPresentation {
    private final TextPanel textPanel = new TextPanel();
    private final Timer timer;
    private final Instant startTime;

    public SessionDurationWidget() {
        this.startTime = Instant.now();
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO update textPanel
            }
        });
        this.timer.start();
    }

    @Override
    public @NotNull String ID() {
        // TODO
        return "";
    }

    @Override
    public @NotNull String getText() {
        // TODO
        return "";
    }

    @Override
    public float getAlignment() {
        // TODO
        return 0;
    }

    @Override
    public @Nullable String getTooltipText() {
        // TODO
        return "";
    }
}