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
            name = "Show Title",
            description = "Show a 'Resource Timer:' heading above the diamond and emerald lines.",
            size = OptionSize.DUAL
    )
    public static boolean showTitle;

    @Switch(
            name = "Show Interval",
            size = OptionSize.DUAL
    )
    public static boolean showInterval;

    @Switch(
            name = "Show Tier Label",
            description = "Append the current tier (e.g. 'II') to each resource line.",
            size = OptionSize.DUAL
    )
    public static boolean showTierLabel;

    @Switch(
            name = "Show Spawn Count",
            description = "Append a running total of how many times each resource has spawned this game (e.g. '(12)').",
            size = OptionSize.DUAL
    )
    public static boolean showSpawnCount;

    @Number(
            name = "Low Timer Warning",
            min = 0, max = 100,
            description = "When the countdown reaches this many seconds the timer turns red. Set to 0 to disable."
    )
    public static int lowValue = 5;

    @HUD(name = "General")
    public static TimerHud hud = new TimerHud();
}
