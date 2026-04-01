package com.github.seal4real.bwresourcetimer.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Header;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.annotations.Number;
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
            name = "Show Title"
    )
    public static boolean showTitle;

    @Switch(
            name = "Show Interval"
    )
    public static boolean showInterval;

    @Switch(
            name = "Show Tier Label"
    )
    public static boolean showTierLabel;

    @Switch(
            name = "Show Spawn Count"
    )
    public static boolean showSpawnCount;

    @Number(
            name = "Low value",
            min = 0, max = 100,
            description = "Beneath this value the timer turns red. Set to zero if you don't want it to turn red."
    )
    public static int lowValue = 5; // default value

    @HUD(name = "General")
    public static TimerHud hud = new TimerHud();
}
