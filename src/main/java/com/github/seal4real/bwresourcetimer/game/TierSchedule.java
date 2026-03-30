package com.github.seal4real.bwresourcetimer.game;

public class TierSchedule {
    public final String name;
    public final int intervalSeconds;
    public final int startsAtSecond;

    public TierSchedule(String name, int intervalSeconds, int startsAtSecond) {
        this.name = name;
        this.intervalSeconds = intervalSeconds;
        this.startsAtSecond = startsAtSecond;
    }
}
