package com.github.seal4real.bwresourcetimer.events;

import com.github.seal4real.bwresourcetimer.game.GameState;
import java.util.Collection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BwEventListener {
    int ticks = -1;
    boolean printed = false;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        ticks = 20; // wait 1 second (20 ticks) before sending /locraw

        // Reset GameState
        GameState.inBedwars = false;
        GameState.mode = null;
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

        if (!printed && Minecraft.getMinecraft().theWorld != null) {
            Scoreboard sb = Minecraft.getMinecraft().theWorld.getScoreboard();
            ScoreObjective obj = sb.getObjectiveInDisplaySlot(1); // sidebar

            if (obj != null) {
                Collection<Score> scores = sb.getSortedScores(obj);
                if (!scores.isEmpty()) {
                    System.out.println("=== SIDEBAR DEBUG ===");
                    System.out.println("TITLE: " + obj.getDisplayName());

                    for (Score score : scores) {
                        String entry = score.getPlayerName();
                        ScorePlayerTeam team = sb.getPlayersTeam(entry);

                        String prefix = (team != null) ? team.getColorPrefix() : "";
                        String suffix = (team != null) ? team.getColorSuffix() : "";

                        String rendered = ScorePlayerTeam.formatPlayerName(team, entry);

                        System.out.println(
                                "[" + prefix + "] | [" + entry + "] | [" + suffix + "]"
                                        + "  ->  " + rendered
                        );
                    }

                    printed = true;
                }
            }
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();

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
