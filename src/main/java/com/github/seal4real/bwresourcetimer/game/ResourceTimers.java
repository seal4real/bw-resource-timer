package com.github.seal4real.bwresourcetimer.game;

public class ResourceTimers {

    public static final TierSchedule[] DIAMOND_TIERS = {
        new TierSchedule("Diamond I",   30,    0),
        new TierSchedule("Diamond II",  23,  360),
        new TierSchedule("Diamond III", 12, 1080),
    };

    // Phantom "initial" entry absorbs the 86s first-spawn delay.
    // Emerald I then takes over at second 86 with the standard 55s interval.
    public static final TierSchedule[] EMERALD_TIERS = {
        new TierSchedule("Emerald N",   86,    0),
        new TierSchedule("Emerald I",   55,   86),
        new TierSchedule("Emerald II",  40,  720),
        new TierSchedule("Emerald III", 27, 1440),
    };

    /** Returns seconds until the next spawn, given elapsed game seconds. */
    public static long secondsUntilNextSpawn(TierSchedule[] tiers, long elapsedSeconds) {
        TierSchedule active = tiers[0];
        for (TierSchedule tier : tiers) {
            if (elapsedSeconds >= tier.startsAtSecond) active = tier;
            else break;
        }
        long elapsedInTier = elapsedSeconds - active.startsAtSecond;
        long nextSpawn = active.startsAtSecond + (elapsedInTier / active.intervalSeconds + 1) * active.intervalSeconds;
        return nextSpawn - elapsedSeconds;
    }
}
