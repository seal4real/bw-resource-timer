package com.github.seal4real.bwresourcetimer.game;

public class TierSchedule {
    public final String tierLabel;
    public final int intervalSeconds;
    public final int startsAtSecond;

    public TierSchedule(String label, int intervalSeconds, int startsAtSecond) {
        this.tierLabel = label;
        this.intervalSeconds = intervalSeconds;
        this.startsAtSecond = startsAtSecond;
    }
}
