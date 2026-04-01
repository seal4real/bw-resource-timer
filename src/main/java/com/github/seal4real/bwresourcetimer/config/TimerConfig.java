package com.github.seal4real.bwresourcetimer.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Header;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import com.github.seal4real.bwresourcetimer.hud.TimerHud;

public class TimerConfig extends Config {
    public TimerConfig() {
        super(new Mod("BW Resource Timer", ModType.HYPIXEL), "bw-resource-timer.json");
        initialize();
    }

    @Switch(
            name = "Show Title",
            size = OptionSize.SINGLE
    )
    public boolean showTitle;

    @HUD(name = "General")
    public static TimerHud hud = new TimerHud();
}
