package com.github.seal4real.bwresourcetimer.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import com.github.seal4real.bwresourcetimer.ExampleMod;
import com.github.seal4real.bwresourcetimer.game.GameState;
import com.github.seal4real.bwresourcetimer.game.ResourceTimers;
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
        if (GameState.gameStartTime < 0) return;

        long elapsed = GameState.getElapsedSeconds();
        long diamondSecs = ResourceTimers.secondsUntilNextSpawn(ResourceTimers.DIAMOND_TIERS, elapsed);
        long emeraldSecs = ResourceTimers.secondsUntilNextSpawn(ResourceTimers.EMERALD_TIERS, elapsed);

        if (ExampleMod.config.showTitle) {
            lines.add("§eResource Timer:");
        }
        lines.add("§bDiamonds: §f" + formatTime(diamondSecs));
        lines.add("§aEmeralds: §f" + formatTime(emeraldSecs));
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
        long seconds = time % 60;
        long minutes = time / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
