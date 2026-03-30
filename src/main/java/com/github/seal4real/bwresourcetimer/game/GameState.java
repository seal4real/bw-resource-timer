package com.github.seal4real.bwresourcetimer.game;

public class GameState {
    public static boolean inBedwars = false;
    public static String mode = null; // e.g. "EIGHT_ONE", "EIGHT_TWO" etc.

    public static boolean gameStarted = false;
    public static long gameStartTime = -1;

    public static long getElapsedSeconds() {
        if (!gameStarted || gameStartTime < 0) return -1;
        return (System.currentTimeMillis() - gameStartTime) / 1000;
    }
}
