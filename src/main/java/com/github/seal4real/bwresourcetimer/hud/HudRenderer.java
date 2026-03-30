package com.github.seal4real.bwresourcetimer.hud;

import com.github.seal4real.bwresourcetimer.game.GameState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudRenderer {

    private static final int X = 10;
    private static final int Y = 10;

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (!GameState.gameStarted) return;

        long elapsed = GameState.getElapsedSeconds();
        long minutes = elapsed / 60;
        long seconds = elapsed % 60;
        String text = String.format("Game: %02d:%02d", minutes, seconds);

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawStringWithShadow(text, X, Y, 0xFFFFFF);
    }
}
