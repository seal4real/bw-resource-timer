package com.github.seal4real.bwresourcetimer.hud;

import com.github.seal4real.bwresourcetimer.game.GameState;
import com.github.seal4real.bwresourcetimer.game.ResourceTimers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudRenderer {

    private static final int X = 10;
    private static final int Y = 10;

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (GameState.gameStartTime < 0) return;

        long elapsed = GameState.getElapsedSeconds();

        long diamondSecs = ResourceTimers.secondsUntilNextSpawn(ResourceTimers.DIAMOND_TIERS, elapsed);
        long emeraldSecs = ResourceTimers.secondsUntilNextSpawn(ResourceTimers.EMERALD_TIERS, elapsed);

        String diamondText = String.format("Diamonds: %d:%02d", diamondSecs / 60, diamondSecs % 60);
        String emeraldText = String.format("Emeralds: %d:%02d", emeraldSecs / 60, emeraldSecs % 60);

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawStringWithShadow(diamondText, X, Y,      0x55FFFF);
        fr.drawStringWithShadow(emeraldText, X, Y + 10, 0x55FF55);
    }
}
