package com.github.seal4real.bwresourcetimer.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BwEventListener {
    int chatCount = 0;

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent event) {
        chatCount++;
        System.out.println(chatCount);
    }

}
