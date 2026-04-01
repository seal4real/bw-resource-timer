package com.github.seal4real.bwresourcetimer.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import com.github.seal4real.bwresourcetimer.ExampleMod;
import com.github.seal4real.bwresourcetimer.game.GameState;
import com.github.seal4real.bwresourcetimer.game.ResourceTimers;
import com.github.seal4real.bwresourcetimer.game.TierSchedule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 *   OneConfig HUD that is started in the config
 */
public class TimerHud extends TextHud {

    public TimerHud() {
        super(true);
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        long elapsed;
        if (example) {
            elapsed = 500; // fake example time
        } else {
            if (GameState.gameStartTime < 0) return;
            elapsed = GameState.getElapsedSeconds();
        }

        TierSchedule activeDiamondTier = ResourceTimers.getActiveTier(ResourceTimers.DIAMOND_TIERS, elapsed);
        TierSchedule activeEmeraldTier = ResourceTimers.getActiveTier(ResourceTimers.EMERALD_TIERS, elapsed);
        long diamondSecs = ResourceTimers.secondsUntilNextSpawn(activeDiamondTier, elapsed);
        long emeraldSecs = ResourceTimers.secondsUntilNextSpawn(activeEmeraldTier, elapsed);

        if (ExampleMod.config.showTitle) {
            lines.add("§eResource Timer:");
        }

        String diamondsLine = "§bDiamonds";
        String emeraldsLine = "§aEmeralds";

        if (ExampleMod.config.showTierLabel) {
            diamondsLine += " " + activeDiamondTier.tierLabel;
            emeraldsLine += " " + activeEmeraldTier.tierLabel;
        }

        diamondsLine += ": §7" + formatTime(diamondSecs);
        emeraldsLine += ": §7" + formatTime(emeraldSecs);

        if (ExampleMod.config.showInterval) {
            diamondsLine += "§f/" + activeDiamondTier.intervalSeconds;
            emeraldsLine += "§f/" + activeEmeraldTier.intervalSeconds;
        }

        if (ExampleMod.config.showSpawnCount) {
            long diamondSpawnCount = ResourceTimers.spawnCount(ResourceTimers.DIAMOND_TIERS, elapsed) + 1; // One diamond spawns at start of game
            long emeraldSpawnCount = ResourceTimers.spawnCount(ResourceTimers.EMERALD_TIERS, elapsed);
            diamondsLine += " §7(" + String.format("%d", diamondSpawnCount) + ")";
            emeraldsLine += " §7(" + String.format("%d", emeraldSpawnCount) + ")";
        }

        lines.add(diamondsLine);
        lines.add(emeraldsLine);
    }

    @Override
    protected void drawLine(String line, float x, float y, float scale) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale, scale, 1);
        fr.drawStringWithShadow(line, 0, 0, 0xFFFFFF);
        GL11.glPopMatrix();
    }

    @Override
    protected float getLineWidth(String line, float scale) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(line) * scale;
    }

    private String formatTime(long time) {
        return String.format("%02d", time);
    }
}
