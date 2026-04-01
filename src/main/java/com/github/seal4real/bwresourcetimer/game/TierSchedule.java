package com.github.seal4real.bwresourcetimer.game;

public class TierSchedule {
    public final String name;
    public final String tierLabel;
    public final int intervalSeconds;
    public final int startsAtSecond;

    public TierSchedule(String name, String tierLabel, int intervalSeconds, int startsAtSecond) {
        this.name = name;
        this.tierLabel = tierLabel;
        this.intervalSeconds = intervalSeconds;
        this.startsAtSecond = startsAtSecond;
    }
}
