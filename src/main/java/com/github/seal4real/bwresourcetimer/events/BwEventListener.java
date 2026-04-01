package com.github.seal4real.bwresourcetimer.events;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import cc.polyfrost.oneconfig.events.event.LocrawEvent;
import cc.polyfrost.oneconfig.events.event.WorldLoadEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import com.github.seal4real.bwresourcetimer.game.GameState;

public class BwEventListener {

    public BwEventListener() {
        EventManager.INSTANCE.register(this);
    }

    @Subscribe
    public void onWorldLoad(WorldLoadEvent event) {
        GameState.inBedwars = false;
        GameState.mode = null;
        GameState.gameStartTime = -1;
    }

    @Subscribe
    public void onLocraw(LocrawEvent event) {
        LocrawInfo info = event.info;
        if (info == null) return;

        GameState.inBedwars = info.getGameType() == LocrawInfo.GameType.BEDWARS;
        GameState.mode = info.getGameMode();
    }

    @Subscribe
    public void onChat(ChatReceiveEvent event) {
        String msg = event.getFullyUnformattedMessage();

        if (msg.contains("Protect your bed")) {
            GameState.gameStartTime = System.currentTimeMillis();
        }
    }
}