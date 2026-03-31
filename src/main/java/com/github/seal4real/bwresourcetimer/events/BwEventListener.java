package com.github.seal4real.bwresourcetimer.events;

import com.github.seal4real.bwresourcetimer.game.GameState;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BwEventListener {
    int ticks = -1;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        ticks = 20; // wait 1 second (20 ticks) before sending /locraw

        // Reset GameState
        GameState.inBedwars = false;
        GameState.mode = null;
        GameState.gameStartTime = -1;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (ticks < 0) return;

        ticks--;
        if (ticks == 0) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw");
            ticks = -1;
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();

        // TODO: replace placeholder with the real Hypixel BedWars game-start message
        if (msg.contains("Protect your bed and destroy the enemy beds")) {
            GameState.gameStartTime = System.currentTimeMillis();
        }

        if (msg.startsWith("{")) {  // /locraw responses are JSON
            event.setCanceled(true); // hide it from chat

            JsonObject json = new JsonParser().parse(msg).getAsJsonObject();

            if (json.has("gametype")) {
                String gameType = json.get("gametype").getAsString();
                String mode = json.has("mode") ? json.get("mode").getAsString() : "unknown";

                GameState.inBedwars = gameType.equals("BEDWARS");
                GameState.mode = mode;

                System.out.println("Game type: " + gameType);
                System.out.println("Mode: " + mode);
            }
        }
    }

}
