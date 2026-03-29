package com.github.seal4real.bwresourcetimer.events;

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
    public void chat(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();
        System.out.println("CHAT: " + msg);
    }

}
