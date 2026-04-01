package com.github.seal4real.bwresourcetimer.game;

public class ResourceTimers {

    public static final TierSchedule[] DIAMOND_TIERS = {
        new TierSchedule("I",   30,    0),
        new TierSchedule("II",  23,  360),
        new TierSchedule("III", 12, 1080),
    };

    // Phantom "initial" entry absorbs the 86s first-spawn delay.
    // Emerald I then takes over at second 86 with the standard 55s interval.
    public static final TierSchedule[] EMERALD_TIERS = {
        new TierSchedule("0",   86,    0),
        new TierSchedule("I",   55,   86),
        new TierSchedule("II",  40,  720),
        new TierSchedule("III", 27, 1440),
    };

    public static TierSchedule getActiveTier(TierSchedule[] tiers, long elapsedSeconds) {
        TierSchedule active = tiers[0];
        for (TierSchedule tier : tiers) {
            if (elapsedSeconds >= tier.startsAtSecond) active = tier;
            else break;
        }
        return active;
    }

    /** Returns seconds until the next spawn, given elapsed game seconds and active tier. */
    public static long secondsUntilNextSpawn(TierSchedule active, long elapsedSeconds) {
        long elapsedInTier = elapsedSeconds - active.startsAtSecond;
        // Integer division floors to the last completed interval; +1 advances to the next one.
        long nextSpawn = active.startsAtSecond + (elapsedInTier / active.intervalSeconds + 1) * active.intervalSeconds;
        return nextSpawn - elapsedSeconds;
    }

    public static long spawnCount(TierSchedule[] tiers, long elapsedSeconds) {
        long count = 0;
        for (int i = 0; i < tiers.length; i++) {
            TierSchedule tier = tiers[i];
            if (elapsedSeconds < tier.startsAtSecond) break;

            // Cap tierEnd at the next tier's start (or now if we're in the last tier)
            long tierEnd = (i + 1 < tiers.length) ? tiers[i + 1].startsAtSecond : elapsedSeconds;
            long tierDuration = Math.min(elapsedSeconds, tierEnd) - tier.startsAtSecond;
            count += tierDuration / tier.intervalSeconds;
        }
        return count;
    }

}
