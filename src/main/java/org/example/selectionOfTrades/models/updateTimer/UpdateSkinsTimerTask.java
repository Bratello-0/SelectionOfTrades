package org.example.selectionOfTrades.models.updateTimer;

import java.util.TimerTask;

public class UpdateSkinsTimerTask extends TimerTask {
    @Override
    public void run() {
        updateSkins.updateSkins();
    }

    public UpdateSkinsTimerTask(UpdateSkins updateSkins) {
        this.updateSkins = updateSkins;
    }

    public UpdateSkins updateSkins;
}
